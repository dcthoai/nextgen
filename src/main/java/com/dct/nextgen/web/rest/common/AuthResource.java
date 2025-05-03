package com.dct.nextgen.web.rest.common;

import com.dct.nextgen.constants.SecurityConstants;
import com.dct.nextgen.dto.request.AuthRequestDTO;
import com.dct.nextgen.dto.response.AuthenticationResponseDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.service.AuthenticationService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/p/common/auth")
public class AuthResource {

    private final AuthenticationService authService;

    public AuthResource(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public BaseResponseDTO login(@Valid @RequestBody AuthRequestDTO requestDTO, HttpServletResponse response) {
        BaseResponseDTO responseDTO = authService.authenticate(requestDTO);
        AuthenticationResponseDTO result = (AuthenticationResponseDTO) responseDTO.getResult();
        Cookie secureCookie = authService.createSecureCookie(result.getToken(), requestDTO.getRememberMe());

        result.setToken(null);            // Clear token in response body
        response.addCookie(secureCookie); // Send secure cookie with token to client in HttpOnly
        responseDTO.setResult(result);

        return responseDTO;
    }

    @PostMapping("/logout")
    public BaseResponseDTO logout(HttpServletResponse response) {
        SecurityContextHolder.getContext().setAuthentication(null);

        // Create cookie with token is null
        Cookie secureCookie = authService.createSecureCookie(null, false);
        secureCookie.setMaxAge(0); // Delete cookies immediately
        response.addCookie(secureCookie); // Send new cookie to client to overwrite old cookie
        response.setHeader(SecurityConstants.HEADER.AUTHORIZATION_HEADER, null);
        response.setHeader(SecurityConstants.HEADER.AUTHORIZATION_GATEWAY_HEADER, null);

        return BaseResponseDTO.builder().ok();
    }
}
