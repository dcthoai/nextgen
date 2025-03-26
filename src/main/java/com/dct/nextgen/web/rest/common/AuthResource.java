package com.dct.nextgen.web.rest.common;

import com.dct.nextgen.constants.ExceptionConstants;
import com.dct.nextgen.constants.HttpStatusConstants;
import com.dct.nextgen.constants.ResultConstants;
import com.dct.nextgen.dto.request.AuthRequestDTO;
import com.dct.nextgen.dto.request.RegisterAccountRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.entity.base.Account;
import com.dct.nextgen.exception.BaseBadRequestException;
import com.dct.nextgen.service.AccountService;
import com.dct.nextgen.service.AuthenticationService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/p/common/auth")
public class AuthResource {

    private static final Logger log = LoggerFactory.getLogger(AuthResource.class);
    private static final String ENTITY_NAME = "AuthResource";
    private final AccountService accountService;
    private final AuthenticationService authService;

    public AuthResource(AccountService accountService,
                        AuthenticationService authService) {
        this.accountService = accountService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public BaseResponseDTO register(@Valid @RequestBody RegisterAccountRequestDTO requestDTO) {
        log.debug("REST request to create an account: {}", requestDTO.getUsername());
        Account account = accountService.createNewAccount(requestDTO);

        if (Objects.isNull(account) || Objects.isNull(account.getId()) || account.getId() < 1)
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.REGISTER_FAILED);

        return BaseResponseDTO.builder()
            .code(HttpStatusConstants.CREATED)
            .success(HttpStatusConstants.STATUS.SUCCESS)
            .message(ResultConstants.REGISTER_SUCCESS)
            .result(account)
            .build();
    }

    @PostMapping("/login")
    public BaseResponseDTO login(@Valid @RequestBody AuthRequestDTO requestDTO, HttpServletResponse response) {
        BaseResponseDTO responseDTO = authService.authenticate(requestDTO);
        String jwt = (String) responseDTO.getResult();
        Cookie secureCookie = authService.createSecureCookie(jwt, requestDTO.getRememberMe());

        response.addCookie(secureCookie); // Send secure cookie with token to client in HttpOnly
        responseDTO.setResult(null); // Clear token in response body

        return responseDTO;
    }

    @PostMapping("/logout")
    public BaseResponseDTO logout(HttpServletResponse response) {
        SecurityContextHolder.getContext().setAuthentication(null);

        // Create cookie with token is null
        Cookie secureCookie = authService.createSecureCookie(null, false);
        secureCookie.setMaxAge(0); // Delete cookies immediately
        response.addCookie(secureCookie); // Send new cookie to client to overwrite old cookie

        return BaseResponseDTO.builder().ok();
    }
}
