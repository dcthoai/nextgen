package com.dct.nextgen.service.impl;

import com.dct.nextgen.common.Common;
import com.dct.nextgen.common.FileUtils;
import com.dct.nextgen.constants.ExceptionConstants;
import com.dct.nextgen.dto.about.PartnerDTO;
import com.dct.nextgen.dto.mapping.IPartnerDTO;
import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.CreateOrUpdatePartnerRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.entity.Partner;
import com.dct.nextgen.exception.BaseBadRequestAlertException;
import com.dct.nextgen.exception.BaseBadRequestException;
import com.dct.nextgen.repositories.PartnerRepository;
import com.dct.nextgen.service.PartnerService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Optional;

@Service
public class PartnerServiceImpl implements PartnerService {

    private static final String ENTITY_NAME = "PartnerServiceImpl";
    private final PartnerRepository partnerRepository;
    private final FileUtils fileUtils;

    public PartnerServiceImpl(PartnerRepository partnerRepository, FileUtils fileUtils) {
        this.partnerRepository = partnerRepository;
        this.fileUtils = fileUtils;
    }

    @Override
    public BaseResponseDTO getAllPartnersWithPaging(BaseRequestDTO request) {
        Page<IPartnerDTO> partnersWithPaged = partnerRepository.findAllWithPaging(request.getPageable());
        return BaseResponseDTO.builder().total(partnersWithPaged.getTotalElements()).ok(partnersWithPaged.getContent());
    }

    @Override
    public BaseResponseDTO getPartnerDetail(Integer partnerId) {
        Optional<Partner> partnerOptional = partnerRepository.findById(partnerId);

        if (partnerOptional.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.DATA_NOT_FOUND);
        }

        Partner partner = partnerOptional.get();
        PartnerDTO partnerDetail = new PartnerDTO();
        BeanUtils.copyProperties(partner, partnerDetail);
        Common.setAuditingInfo(partner, partnerDetail);

        return BaseResponseDTO.builder().ok(partnerDetail);
    }

    @Override
    public BaseResponseDTO createNewPartner(CreateOrUpdatePartnerRequestDTO request) {
        if (FileUtils.invalidUploadFile(request.getLogoFile())) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.INVALID_REQUEST_DATA);
        }

        String imageUrl = fileUtils.autoCompressImageAndSave(request.getLogoFile());

        if (!StringUtils.hasText(imageUrl)) {
            throw new BaseBadRequestAlertException(ENTITY_NAME, ExceptionConstants.CANNOT_SAVE_IMAGE);
        }

        Partner partner = new Partner();
        BeanUtils.copyProperties(request, partner);
        partner.setLogo(imageUrl);
        partnerRepository.save(partner);

        return BaseResponseDTO.builder().ok(partner);
    }

    @Override
    public BaseResponseDTO updatePartner(CreateOrUpdatePartnerRequestDTO request) {
        if (Objects.isNull(request.getId()) || request.getId() <= 0) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.INVALID_REQUEST_DATA);
        }

        Partner partner = partnerRepository.findById(request.getId()).orElse(null);

        if (Objects.isNull(partner)) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.DATA_NOT_FOUND);
        }

        BeanUtils.copyProperties(request, partner, "logo");
        String imageUrl = fileUtils.autoCompressImageAndSave(request.getLogoFile());

        if (StringUtils.hasText(imageUrl)) {
            fileUtils.delete(partner.getLogo());
            partner.setLogo(imageUrl);
        }

        return BaseResponseDTO.builder().ok(partnerRepository.save(partner));
    }

    @Override
    public BaseResponseDTO deletePartner(Integer partnerId) {
        if (Objects.isNull(partnerId) || partnerId <= 0) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.INVALID_REQUEST_DATA);
        }

        partnerRepository.deleteById(partnerId);

        return BaseResponseDTO.builder().ok();
    }
}
