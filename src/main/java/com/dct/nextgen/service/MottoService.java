package com.dct.nextgen.service;

import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.CreateOrUpdateMottoRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;

public interface MottoService {

    BaseResponseDTO getAllMottosWithPaging(BaseRequestDTO request);
    BaseResponseDTO getMottoDetail(Integer mottoId);
    BaseResponseDTO createNewMotto(CreateOrUpdateMottoRequestDTO request);
    BaseResponseDTO updateMotto(CreateOrUpdateMottoRequestDTO request);
    BaseResponseDTO deleteMotto(Integer mottoId);
}
