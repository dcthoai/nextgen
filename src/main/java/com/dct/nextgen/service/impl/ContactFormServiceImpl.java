package com.dct.nextgen.service.impl;

import com.dct.nextgen.common.Common;
import com.dct.nextgen.constants.ExceptionConstants;
import com.dct.nextgen.dto.about.ContactFormDTO;
import com.dct.nextgen.dto.mapping.IContactFormDTO;
import com.dct.nextgen.dto.request.UpdateContactFormRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.entity.ContactForm;
import com.dct.nextgen.exception.BaseBadRequestException;
import com.dct.nextgen.repositories.ContactFormRepository;
import com.dct.nextgen.service.ContactFormService;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ContactFormServiceImpl implements ContactFormService {

    private static final String ENTITY_NAME = "ContactFormServiceImpl";
    private final ContactFormRepository contactFormRepository;

    public ContactFormServiceImpl(ContactFormRepository contactFormRepository) {
        this.contactFormRepository = contactFormRepository;
    }

    @Override
    public BaseResponseDTO getContactFormDetail() {
        Optional<ContactForm> contactFormOptional = contactFormRepository.findContactFormDetail();

        if (contactFormOptional.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.DATA_NOT_FOUND);
        }

        ContactForm contactForm = contactFormOptional.get();
        ContactFormDTO contactFormDetail = new ContactFormDTO();
        BeanUtils.copyProperties(contactForm, contactFormDetail);
        Common.setAuditingInfo(contactForm, contactFormDetail);

        return BaseResponseDTO.builder().ok(contactFormDetail);
    }

    @Override
    public BaseResponseDTO getContactFormInfo() {
        Optional<IContactFormDTO> contactFormOptional = contactFormRepository.findContactForm();

        if (contactFormOptional.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.DATA_NOT_FOUND);
        }

        return BaseResponseDTO.builder().ok(contactFormOptional.get());
    }

    @Override
    @Transactional
    public BaseResponseDTO updateContactForm(UpdateContactFormRequestDTO request) {
        ContactForm contactForm = contactFormRepository.findContactFormDetail().orElseGet(ContactForm::new);
        BeanUtils.copyProperties(request, contactForm, "id");
        return BaseResponseDTO.builder().ok(contactFormRepository.save(contactForm));
    }
}
