package com.dct.nextgen.service;

import com.dct.nextgen.dto.request.CreateCustomerDemandRequestDTO;
import com.dct.nextgen.dto.request.CustomerDemandSearchRequestDTO;
import com.dct.nextgen.dto.request.UpdateCustomerDemandStatusRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;

public interface CustomerDemandService {

    BaseResponseDTO getAllCustomerDemandsWithPaging(CustomerDemandSearchRequestDTO request);

    BaseResponseDTO getCustomerDemandDetail(Integer demandId);

    BaseResponseDTO createNewCustomerDemand(CreateCustomerDemandRequestDTO request);

    BaseResponseDTO updateCustomerDemandStatus(UpdateCustomerDemandStatusRequestDTO request);

    BaseResponseDTO deleteCustomerDemand(Integer demandId);
}
