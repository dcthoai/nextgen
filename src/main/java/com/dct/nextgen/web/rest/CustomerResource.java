package com.dct.nextgen.web.rest;

import com.dct.nextgen.dto.response.BaseResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerResource {

    private static final Logger log = LoggerFactory.getLogger(CustomerResource.class);

    @GetMapping
    public BaseResponseDTO getAllCustomersWithPaging() {

        return BaseResponseDTO.builder().ok();
    }

    @GetMapping("/{customerID}")
    public BaseResponseDTO getCustomerDetail(@PathVariable Integer customerID) {

        return BaseResponseDTO.builder().ok();
    }

    @PostMapping
    public BaseResponseDTO createNewCustomer() {

        return BaseResponseDTO.builder().ok();
    }

    @PutMapping
    public BaseResponseDTO updateCustomer() {

        return BaseResponseDTO.builder().ok();
    }

    @DeleteMapping("/{customerID}")
    public BaseResponseDTO deleteCustomer(@PathVariable Integer customerID) {

        return BaseResponseDTO.builder().ok();
    }
}
