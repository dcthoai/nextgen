package com.dct.nextgen.service;

import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.CreateOrUpdatePartnerRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;

public interface PartnerService {

    BaseResponseDTO getAllPartnersWithPaging(BaseRequestDTO request);
    BaseResponseDTO getPartnerDetail(Integer partnerId);
    BaseResponseDTO createNewPartner(CreateOrUpdatePartnerRequestDTO request);
    BaseResponseDTO updatePartner(CreateOrUpdatePartnerRequestDTO request);
    BaseResponseDTO deletePartner(Integer partnerId);
}
