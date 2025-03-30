package com.dct.nextgen.service.impl;

import com.dct.nextgen.config.properties.Security;
import com.dct.nextgen.constants.ExceptionConstants;
import com.dct.nextgen.constants.HttpStatusConstants;
import com.dct.nextgen.constants.ResultConstants;
import com.dct.nextgen.constants.SecurityConstants;
import com.dct.nextgen.dto.auth.BaseAuthTokenDTO;
import com.dct.nextgen.dto.request.AuthRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.entity.base.Account;
import com.dct.nextgen.exception.BaseAuthenticationException;
import com.dct.nextgen.exception.BaseBadRequestException;
import com.dct.nextgen.repositories.common.AccountRepository;
import com.dct.nextgen.security.model.CustomUserDetails;
import com.dct.nextgen.security.jwt.JwtProvider;
import com.dct.nextgen.service.AuthenticationService;

import jakarta.servlet.http.Cookie;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    private static final String ENTITY_NAME = "AuthenticationServiceImpl";
    private final AuthenticationManager authenticationManager;
    private final AccountRepository accountRepository;
    private final JwtProvider tokenProvider;
    private final Security security;

    public AuthenticationServiceImpl(JwtProvider tokenProvider,
                                     AuthenticationManager authenticationManager,
                                     AccountRepository accountRepository,
                                     @Qualifier("security") Security security) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.accountRepository = accountRepository;
        this.security = security;
    }

    private Authentication authenticate(String username, String rawPassword) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, rawPassword);

        try {
            return authenticationManager.authenticate(token);
        } catch (UsernameNotFoundException e) {
            log.error("[{}] Account '{}' does not exists", e.getClass().getSimpleName(), username);
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.ACCOUNT_NOT_FOUND);
        } catch (DisabledException e) {
            log.error("[{}] - {}", e.getClass().getSimpleName(), e.getMessage());
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.ACCOUNT_DISABLED);
        } catch (LockedException e) {
            log.error("[{}] - {}", e.getClass().getSimpleName(), e.getMessage());
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.ACCOUNT_LOCKED);
        } catch (AccountExpiredException e) {
            log.error("[{}] - {}", e.getClass().getSimpleName(), e.getMessage());
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.ACCOUNT_EXPIRED);
        } catch (CredentialsExpiredException e) {
            log.error("[{}] - {}", e.getClass().getSimpleName(), e.getMessage());
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.CREDENTIALS_EXPIRED);
        } catch (BadCredentialsException e) {
            log.error("[{}] - {}", e.getClass().getSimpleName(), e.getMessage());
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.BAD_CREDENTIALS);
        } catch (AuthenticationException e) {
            log.error("[{}] Authentication failed: {}", e.getClass().getSimpleName(), e.getMessage());
            throw new BaseAuthenticationException(ENTITY_NAME, ExceptionConstants.UNAUTHORIZED);
        }
    }

    @Override
    @Transactional
    public BaseResponseDTO authenticate(AuthRequestDTO authRequestDTO) {
        log.debug("Authenticating user: {}", authRequestDTO.getUsername());
        String username = authRequestDTO.getUsername().trim();
        String rawPassword = authRequestDTO.getPassword().trim();

        Authentication authentication = authenticate(username, rawPassword);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Account account = userDetails.getAccount();
        String newDeviceId = authRequestDTO.getDeviceId();

        if (!StringUtils.hasText(newDeviceId)) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.DEVICE_ID_NOT_BLANK);
        } else if (!Objects.equals(account.getDeviceId(), newDeviceId)) {
            accountRepository.updateDeviceIdByAccountId(account.getId(), authRequestDTO.getDeviceId());
        }

        BaseAuthTokenDTO authTokenDTO = BaseAuthTokenDTO.builder()
            .authentication(authentication)
            .userId(account.getId())
            .deviceId(newDeviceId)
            .rememberMe(authRequestDTO.getRememberMe())
            .build();

        String jwtToken = tokenProvider.createToken(authTokenDTO);

        return BaseResponseDTO.builder()
            .code(HttpStatusConstants.ACCEPTED)
            .message(ResultConstants.LOGIN_SUCCESS)
            .success(HttpStatusConstants.STATUS.SUCCESS)
            .result(jwtToken)
            .build();
    }

    @Override
    public Cookie createSecureCookie(String token, boolean isRememberMe) {
        long tokenValidityInMilliseconds = isRememberMe
            ? security.getTokenValidityForRememberMe()
            : security.getTokenValidity();

        Cookie tokenCookie = new Cookie(SecurityConstants.COOKIES.HTTP_ONLY_COOKIE_ACCESS_TOKEN, token);
        tokenCookie.setHttpOnly(true); // Prevent access by Javascript
        tokenCookie.setSecure(security.isEnabledTls()); // Set true for HTTPS protocol only
        tokenCookie.setPath("/"); // Apply for all requests
        tokenCookie.setMaxAge((int) (tokenValidityInMilliseconds / 1000L));
        tokenCookie.setAttribute("SameSite", "Lax"); // Avoid CSRF but still allow requests from subdomains

        return tokenCookie;
    }
}
