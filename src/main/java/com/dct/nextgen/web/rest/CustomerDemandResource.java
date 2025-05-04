package com.dct.nextgen.web.rest;

import com.dct.nextgen.dto.request.CreateCustomerDemandRequestDTO;
import com.dct.nextgen.dto.request.CustomerDemandSearchRequestDTO;
import com.dct.nextgen.dto.request.UpdateCustomerDemandStatusRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.service.CustomerDemandService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerDemandResource {

    private final CustomerDemandService customerDemandService;

    public CustomerDemandResource(CustomerDemandService customerDemandService) {
        this.customerDemandService = customerDemandService;
    }

    @GetMapping("/customers/demands")
    public BaseResponseDTO getAllCustomerDemandsWithPaging(@ModelAttribute CustomerDemandSearchRequestDTO request) {
        return customerDemandService.getAllCustomerDemandsWithPaging(request);
    }

    @GetMapping("/customers/demands/{demandId}")
    public BaseResponseDTO getCustomerDemandDetail(@PathVariable Integer demandId) {
        return customerDemandService.getCustomerDemandDetail(demandId);
    }

    @PostMapping("/p/customers/demands")
    public BaseResponseDTO createNewCustomerDemand(@Valid @RequestBody CreateCustomerDemandRequestDTO requestDTO) {
        return customerDemandService.createNewCustomerDemand(requestDTO);
    }

    @PutMapping("/customers/demands/status")
    public BaseResponseDTO updateCustomerDemandStatus(@Valid @RequestBody UpdateCustomerDemandStatusRequestDTO request) {
        return customerDemandService.updateCustomerDemandStatus(request);
    }

    @DeleteMapping("/customers/demands/{demandId}")
    public BaseResponseDTO deleteCustomerDemand(@PathVariable Integer demandId) {
        return customerDemandService.deleteCustomerDemand(demandId);
    }
}
