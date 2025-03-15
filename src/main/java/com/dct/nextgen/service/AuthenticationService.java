package com.dct.nextgen.service;

import com.dct.nextgen.dto.request.AuthRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;

public interface AuthenticationService {

    BaseResponseDTO authenticate(AuthRequestDTO authRequestDTO);
}
