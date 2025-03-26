package com.dct.nextgen.service;

import com.dct.nextgen.dto.request.AuthRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import jakarta.servlet.http.Cookie;

public interface AuthenticationService {

    BaseResponseDTO authenticate(AuthRequestDTO authRequestDTO);
    Cookie createSecureCookie(String token, boolean isRememberMe);
}
