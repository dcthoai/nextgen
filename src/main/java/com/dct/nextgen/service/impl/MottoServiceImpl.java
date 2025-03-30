package com.dct.nextgen.service.impl;

import com.dct.nextgen.common.Common;
import com.dct.nextgen.constants.ExceptionConstants;
import com.dct.nextgen.dto.about.MottoDTO;
import com.dct.nextgen.dto.mapping.IMottoDTO;
import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.CreateOrUpdateMottoRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.entity.Motto;
import com.dct.nextgen.exception.BaseBadRequestException;
import com.dct.nextgen.repositories.MottoRepository;
import com.dct.nextgen.service.MottoService;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class MottoServiceImpl implements MottoService {

    private static final String ENTITY_NAME = "MottoServiceImpl";
    private final MottoRepository mottoRepository;

    public MottoServiceImpl(MottoRepository mottoRepository) {
        this.mottoRepository = mottoRepository;
    }

    @Override
    public BaseResponseDTO getAllMottosWithPaging(BaseRequestDTO request) {
        if (request.getPageable().isPaged()) {
            Page<IMottoDTO> mottosWithPaged = mottoRepository.findAllWithPaging(request.getPageable());
            return BaseResponseDTO.builder().total(mottosWithPaged.getTotalElements()).ok(mottosWithPaged.getContent());
        }

        return BaseResponseDTO.builder().ok(mottoRepository.findAllNonPaging());
    }

    @Override
    public BaseResponseDTO getMottoDetail(Integer mottoId) {
        Optional<Motto> mottoOptional = mottoRepository.findById(mottoId);

        if (mottoOptional.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.DATA_NOT_FOUND);
        }

        Motto motto = mottoOptional.get();
        MottoDTO mottoDetail = new MottoDTO();
        BeanUtils.copyProperties(motto, mottoDetail);
        Common.setAuditingInfo(motto, mottoDetail);

        return BaseResponseDTO.builder().ok(mottoDetail);
    }

    @Override
    public BaseResponseDTO createNewMotto(CreateOrUpdateMottoRequestDTO request) {
        Motto motto = new Motto();
        BeanUtils.copyProperties(request, motto, "id");
        return BaseResponseDTO.builder().ok(mottoRepository.save(motto));
    }

    @Override
    public BaseResponseDTO updateMotto(CreateOrUpdateMottoRequestDTO request) {
        Optional<Motto> mottoOptional = mottoRepository.findById(request.getId());

        if (mottoOptional.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.DATA_NOT_FOUND);
        }

        Motto motto = mottoOptional.get();
        BeanUtils.copyProperties(request, motto);

        return BaseResponseDTO.builder().ok(mottoRepository.save(motto));
    }

    @Override
    public BaseResponseDTO deleteMotto(Integer mottoId) {
        if (Objects.isNull(mottoId) || mottoId <= 0) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.INVALID_REQUEST_DATA);
        }

        mottoRepository.deleteById(mottoId);

        return BaseResponseDTO.builder().ok();
    }
}
