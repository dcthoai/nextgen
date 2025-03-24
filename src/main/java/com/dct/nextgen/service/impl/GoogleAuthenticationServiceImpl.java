package com.dct.nextgen.service.impl;

import com.dct.nextgen.common.CredentialGenerator;
import com.dct.nextgen.constants.ExceptionConstants;
import com.dct.nextgen.constants.HttpStatusConstants;
import com.dct.nextgen.constants.PropertiesConstants;
import com.dct.nextgen.constants.ResultConstants;
import com.dct.nextgen.constants.SecurityConstants;
import com.dct.nextgen.dto.auth.BaseAuthTokenDTO;
import com.dct.nextgen.dto.request.RegisterAccountRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.entity.base.Account;
import com.dct.nextgen.exception.BaseAuthenticationException;
import com.dct.nextgen.repositories.common.AccountRepository;
import com.dct.nextgen.security.jwt.JwtProvider;
import com.dct.nextgen.security.model.OAuth2TokenResponse;
import com.dct.nextgen.security.model.OAuth2UserInfoResponse;
import com.dct.nextgen.security.service.GoogleOAuth2Service;
import com.dct.nextgen.service.AccountService;
import com.dct.nextgen.service.GoogleAuthenticationService;

import jakarta.servlet.http.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.Set;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

@Service
@ConditionalOnProperty(name = PropertiesConstants.OAUTH2_ACTIVE_STATUS, havingValue = "true")
public class GoogleAuthenticationServiceImpl implements GoogleAuthenticationService {

    private static final Logger log = LoggerFactory.getLogger(GoogleAuthenticationServiceImpl.class);
    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final JwtProvider tokenProvider;
    private final GoogleOAuth2Service googleOAuth2Service;

    public GoogleAuthenticationServiceImpl(AccountService accountService,
                                           AccountRepository accountRepository,
                                           JwtProvider tokenProvider,
                                           GoogleOAuth2Service googleOAuth2Service) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
        this.tokenProvider = tokenProvider;
        this.googleOAuth2Service = googleOAuth2Service;
    }

    @Override
    public BaseResponseDTO authorize(String code) {
        log.debug("Get authorization information from Google");

        if (!StringUtils.hasText(code))
            throw new BaseAuthenticationException(ENTITY_NAME, ExceptionConstants.BAD_CREDENTIALS);

        OAuth2TokenResponse tokenResponse = googleOAuth2Service.getAccessToken(code);
        OAuth2UserInfoResponse userInfo = googleOAuth2Service.getUserInfo(tokenResponse.getAccessToken());

        return authorize(userInfo);
    }

    @Override
    public BaseResponseDTO authorize(OAuth2UserInfoResponse userInfo) {
        log.debug("Authorize for user '{}'", userInfo.getEmail());
        Optional<Account> accountOptional = accountRepository.findByEmail(userInfo.getEmail());
        Account account;
        String username, password;

        if (accountOptional.isEmpty()) {
            username = CredentialGenerator.generateUsername(8);
            password = CredentialGenerator.generatePassword(8);
            log.debug("Authenticate for user '{}' via Google, no account yet", username);

            RegisterAccountRequestDTO registerAccountRequest = new RegisterAccountRequestDTO();
            registerAccountRequest.setUsername(username);
            registerAccountRequest.setEmail(userInfo.getEmail());
            registerAccountRequest.setPassword(password);

            account = accountService.createNewAccount(registerAccountRequest);
        } else {
            account = accountOptional.get();
            username = account.getUsername();
        }

        Set<SimpleGrantedAuthority> authorities = Set.of(new SimpleGrantedAuthority(SecurityConstants.ROLES.ROLE_USER));
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, username, authorities);
        SecurityContextHolder.getContext().setAuthentication(token);
        BaseAuthTokenDTO authTokenDTO = new BaseAuthTokenDTO(token, account);

        log.debug("Authorize successful. Generating token for user '{}'", username);
        String jwtToken = tokenProvider.createToken(authTokenDTO);
        Cookie tokenCookie = new Cookie(SecurityConstants.COOKIES.HTTP_ONLY_COOKIE_ACCESS_TOKEN, jwtToken);

        return BaseResponseDTO.builder()
            .code(HttpStatusConstants.ACCEPTED)
            .message(ResultConstants.LOGIN_SUCCESS)
            .success(HttpStatusConstants.STATUS.SUCCESS)
            .result(tokenCookie)
            .build();
    }
}
