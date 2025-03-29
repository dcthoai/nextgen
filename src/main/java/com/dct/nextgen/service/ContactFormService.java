package com.dct.nextgen.service;

import com.dct.nextgen.dto.request.UpdateContactFormRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;

public interface ContactFormService {

    BaseResponseDTO getContactFormDetail();
    BaseResponseDTO getContactFormInfo();
    BaseResponseDTO updateContactForm(UpdateContactFormRequestDTO request);
}
