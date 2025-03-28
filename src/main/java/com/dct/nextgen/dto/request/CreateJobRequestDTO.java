package com.dct.nextgen.dto.request;

import com.dct.nextgen.constants.BaseConstants;
import com.dct.nextgen.constants.ExceptionConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateJobRequestDTO extends BaseRequestDTO {

    @NotBlank(message = ExceptionConstants.NAME_NOT_BLANK)
    @Size(max = 500, message = ExceptionConstants.NAME_MAX_LENGTH)
    private String name;

    @NotBlank(message = ExceptionConstants.DESCRIPTION_NOT_BLANK)
    @Size(max = 1500, message = ExceptionConstants.DESCRIPTION_MAX_LENGTH)
    private String description;

    @NotBlank(message = ExceptionConstants.EMAIL_NOT_BLANK)
    @Size(min = 6, message = ExceptionConstants.EMAIL_MIN_LENGTH)
    @Size(max = 100, message = ExceptionConstants.EMAIL_MAX_LENGTH)
    @Pattern(regexp = BaseConstants.REGEX.EMAIL_PATTERN, message = ExceptionConstants.EMAIL_INVALID)
    private String contactMail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactMail() {
        return contactMail;
    }

    public void setContactMail(String contactMail) {
        this.contactMail = contactMail;
    }
}
