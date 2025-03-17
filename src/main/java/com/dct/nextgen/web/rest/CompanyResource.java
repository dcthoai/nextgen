package com.dct.nextgen.web.rest;

import com.dct.nextgen.dto.response.BaseResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company")
public class CompanyResource {

    private static final Logger log = LoggerFactory.getLogger(CompanyResource.class);

    @GetMapping
    public BaseResponseDTO getCompanyInfo() {

        return new BaseResponseDTO();
    }

    @PutMapping
    public BaseResponseDTO updateCompanyInfo() {

        return new BaseResponseDTO();
    }
}
