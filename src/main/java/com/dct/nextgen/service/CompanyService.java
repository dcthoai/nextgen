package com.dct.nextgen.service;

import com.dct.nextgen.dto.request.UpdateCompanyRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;

public interface CompanyService {

    BaseResponseDTO getCompanyInfo();
    BaseResponseDTO getCompanyDetail();
    BaseResponseDTO updateCompanyInfo(UpdateCompanyRequestDTO request);
}
