package com.dct.nextgen.web.rest;

import com.dct.nextgen.aop.annotation.CheckAuthorize;
import com.dct.nextgen.constants.RoleConstants;
import com.dct.nextgen.dto.request.UpdateCompanyRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.service.CompanyService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CompanyResource {

    private final CompanyService companyService;

    public CompanyResource(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/p/company")
    public BaseResponseDTO getCompanyInfo() {
        return companyService.getCompanyInfo();
    }

    @PutMapping("/company")
    @CheckAuthorize(authorities = RoleConstants.Company.UPDATE)
    public BaseResponseDTO updateCompanyInfo(@Valid @ModelAttribute UpdateCompanyRequestDTO requestDTO) {
        return companyService.updateCompanyInfo(requestDTO);
    }
}
