package com.dct.nextgen.service.impl;

import com.dct.nextgen.common.Common;
import com.dct.nextgen.common.FileUtils;
import com.dct.nextgen.constants.ExceptionConstants;
import com.dct.nextgen.dto.company.CompanyDTO;
import com.dct.nextgen.dto.mapping.ICompanyDTO;
import com.dct.nextgen.dto.request.UpdateCompanyRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.entity.Company;
import com.dct.nextgen.exception.BaseBadRequestException;
import com.dct.nextgen.repositories.CompanyRepository;
import com.dct.nextgen.service.CompanyService;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private static final String ENTITY_NAME = "CompanyServiceImpl";
    private final CompanyRepository companyRepository;
    private final FileUtils fileUtils;

    public CompanyServiceImpl(CompanyRepository companyRepository, FileUtils fileUtils) {
        this.companyRepository = companyRepository;
        this.fileUtils = fileUtils;
    }

    @Override
    public BaseResponseDTO getCompanyInfo() {
        Optional<ICompanyDTO> companyOptional = companyRepository.findCompany();

        if (companyOptional.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.DATA_NOT_FOUND);
        }

        return BaseResponseDTO.builder().ok(companyOptional.get());
    }

    @Override
    public BaseResponseDTO getCompanyDetail() {
        Optional<Company> companyOptional = companyRepository.findFirstByOrderByCreatedDateAsc();

        if (companyOptional.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.DATA_NOT_FOUND);
        }

        Company company = companyOptional.get();
        CompanyDTO companyDetail = new CompanyDTO();
        BeanUtils.copyProperties(company, companyDetail);
        Common.setAuditingInfo(company, companyDetail);

        return BaseResponseDTO.builder().ok(companyDetail);
    }

    @Override
    @Transactional
    public BaseResponseDTO updateCompanyInfo(UpdateCompanyRequestDTO request) {
        Optional<Company> companyOptional = companyRepository.findFirstByOrderByCreatedDateAsc();
        List<String> oldImageUrlToDelete = new ArrayList<>();
        Company company;

        if (companyOptional.isEmpty()) {
            company = new Company();
        } else {
            company = companyOptional.get();
            oldImageUrlToDelete.add(company.getLogo());
            oldImageUrlToDelete.add(company.getImage());
            oldImageUrlToDelete.add(company.getMapImage());
        }

        BeanUtils.copyProperties(request, company);
        String companyLogoUrl = fileUtils.autoCompressImageAndSave(request.getLogoFile());
        String companyImageUrl = fileUtils.autoCompressImageAndSave(request.getImageFile());
        String companyMapImageUrl = fileUtils.autoCompressImageAndSave(request.getMapImageFile());

        if (StringUtils.hasText(companyLogoUrl)) {
            company.setLogo(companyLogoUrl);
        }

        if (StringUtils.hasText(companyImageUrl)) {
            company.setImage(companyImageUrl);
        }

        if (StringUtils.hasText(companyMapImageUrl)) {
            company.setMapImage(companyMapImageUrl);
        }

        companyRepository.save(company);
        fileUtils.delete(oldImageUrlToDelete);

        return BaseResponseDTO.builder().ok();
    }
}
