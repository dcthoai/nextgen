package com.dct.nextgen.service.impl;

import com.dct.nextgen.dto.request.CreateCustomerDemandRequestDTO;
import com.dct.nextgen.dto.request.CustomerDemandSearchRequestDTO;
import com.dct.nextgen.dto.request.UpdateCustomerDemandStatusRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.repositories.CustomerDemandRepository;
import com.dct.nextgen.service.CustomerDemandService;
import org.springframework.stereotype.Service;

@Service
public class CustomerDemandServiceImpl implements CustomerDemandService {

    private static final String ENTITY_NAME = "CustomerDemandServiceImpl";
    private final CustomerDemandRepository customerDemandRepository;

    public CustomerDemandServiceImpl(CustomerDemandRepository customerDemandRepository) {
        this.customerDemandRepository = customerDemandRepository;
    }

    @Override
    public BaseResponseDTO getAllCustomerDemandsWithPaging(CustomerDemandSearchRequestDTO request) {
        return null;
    }

    @Override
    public BaseResponseDTO getCustomerDemandDetail(Integer demandId) {
        return null;
    }

    @Override
    public BaseResponseDTO createNewCustomerDemand(CreateCustomerDemandRequestDTO request) {
        return null;
    }

    @Override
    public BaseResponseDTO updateCustomerDemandStatus(UpdateCustomerDemandStatusRequestDTO request) {
        return null;
    }

    @Override
    public BaseResponseDTO deleteCustomerDemand(Integer demandId) {
        return null;
    }
}
