package com.dct.nextgen.service.impl;

import com.dct.nextgen.constants.CustomerDemandConstants;
import com.dct.nextgen.constants.ExceptionConstants;
import com.dct.nextgen.dto.mapping.ICustomerDemand;
import com.dct.nextgen.dto.request.CreateCustomerDemandRequestDTO;
import com.dct.nextgen.dto.request.CustomerDemandSearchRequestDTO;
import com.dct.nextgen.dto.request.UpdateCustomerDemandStatusRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.entity.CustomerDemand;
import com.dct.nextgen.exception.BaseBadRequestException;
import com.dct.nextgen.repositories.CustomerDemandRepository;
import com.dct.nextgen.service.CustomerDemandService;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerDemandServiceImpl implements CustomerDemandService {

    private static final String ENTITY_NAME = "CustomerDemandServiceImpl";
    private final CustomerDemandRepository customerDemandRepository;

    public CustomerDemandServiceImpl(CustomerDemandRepository customerDemandRepository) {
        this.customerDemandRepository = customerDemandRepository;
    }

    @Override
    public BaseResponseDTO getAllCustomerDemandsWithPaging(CustomerDemandSearchRequestDTO request) {
        Page<ICustomerDemand> accountsWithPaged = customerDemandRepository.findAllWithPaging(
            request.getStatusSearch(CustomerDemandConstants.Status.PATTERN),
            request.getPhone(),
            request.getEmail(),
            request.getKeywordSearch(),
            request.getFromDateSearch(),
            request.getToDateSearch(),
            request.getPageable()
        );

        List<ICustomerDemand> accounts = accountsWithPaged.getContent();
        return BaseResponseDTO.builder().total(accountsWithPaged.getTotalElements()).ok(accounts);
    }

    @Override
    public BaseResponseDTO getCustomerDemandDetail(Integer demandId) {
        Optional<ICustomerDemand> customerDemandOptional = customerDemandRepository.findDemandById(demandId);

        if (customerDemandOptional.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.CUSTOMER_DEMAND_NOT_EXISTED);
        }

        return BaseResponseDTO.builder().ok(customerDemandOptional.get());
    }

    @Override
    @Transactional
    public BaseResponseDTO createNewCustomerDemand(CreateCustomerDemandRequestDTO request) {
        CustomerDemand customerDemand = new CustomerDemand();
        BeanUtils.copyProperties(request, customerDemand, "id");
        customerDemand.setStatus(CustomerDemandConstants.Status.PENDING);
        customerDemandRepository.save(customerDemand);
        return BaseResponseDTO.builder().ok(customerDemand);
    }

    @Override
    @Transactional
    public BaseResponseDTO updateCustomerDemandStatus(UpdateCustomerDemandStatusRequestDTO request) {
        Optional<CustomerDemand> customerDemandOptional = customerDemandRepository.findById(request.getDemandId());

        if (customerDemandOptional.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.CUSTOMER_DEMAND_NOT_EXISTED);
        }

        CustomerDemand customerDemand = customerDemandOptional.get();
        customerDemand.setStatus(request.getStatus());
        customerDemandRepository.save(customerDemand);

        return BaseResponseDTO.builder().ok();
    }

    @Override
    @Transactional
    public BaseResponseDTO deleteCustomerDemand(Integer demandId) {
        if (Objects.isNull(demandId) || demandId <= 0) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.INVALID_REQUEST_DATA);
        }

        customerDemandRepository.updateDemandStatusById(demandId, CustomerDemandConstants.Status.DELETED);
        return BaseResponseDTO.builder().ok();
    }
}
