package com.dct.nextgen.service.impl;

import com.dct.nextgen.common.CredentialGenerator;
import com.dct.nextgen.constants.ExceptionConstants;
import com.dct.nextgen.constants.HttpStatusConstants;
import com.dct.nextgen.constants.PropertiesConstants;
import com.dct.nextgen.constants.ResultConstants;
import com.dct.nextgen.dto.auth.BaseAuthTokenDTO;
import com.dct.nextgen.dto.mapping.IAuthenticationDTO;
import com.dct.nextgen.dto.request.CreateAccountRequestDTO;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

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
        Optional<IAuthenticationDTO> authentication = accountRepository.findAuthenticationByEmail(userInfo.getEmail());
        Account account = new Account();
        String username, password;

        if (authentication.isEmpty()) {
            username = CredentialGenerator.generateUsername(8);
            password = CredentialGenerator.generatePassword(8);
            log.debug("Authenticate for user '{}' via Google, no account yet", username);

            CreateAccountRequestDTO createAccountRequest = new CreateAccountRequestDTO();
            createAccountRequest.setUsername(username);
            createAccountRequest.setEmail(userInfo.getEmail());
            createAccountRequest.setPassword(password);

            account = accountService.createNewAccount(createAccountRequest);
        } else {
            BeanUtils.copyProperties(authentication.get(), account);
            username = account.getUsername();
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null);
        SecurityContextHolder.getContext().setAuthentication(token);
        BaseAuthTokenDTO authTokenDTO = BaseAuthTokenDTO.builder()
            .authentication(token)
            .userId(account.getId())
            .rememberMe(true)
            .build();

        log.debug("Authorize successful. Generating token for user '{}'", username);
        String jwtToken = tokenProvider.createToken(authTokenDTO);

        return BaseResponseDTO.builder()
            .code(HttpStatusConstants.ACCEPTED)
            .message(ResultConstants.LOGIN_SUCCESS)
            .success(HttpStatusConstants.STATUS.SUCCESS)
            .result(jwtToken)
            .build();
    }
}
