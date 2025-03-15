package com.dct.nextgen.service;

import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.security.model.OAuth2UserInfoResponse;

@SuppressWarnings("unused")
public interface GoogleAuthenticationService {

    BaseResponseDTO authorize(String code);
    BaseResponseDTO authorize(OAuth2UserInfoResponse userInfo);
}
