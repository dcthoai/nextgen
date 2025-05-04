package com.dct.nextgen.dto.request;

import com.dct.nextgen.constants.BaseConstants;
import com.dct.nextgen.constants.ExceptionConstants;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateCustomerDemandRequestDTO {

    @NotBlank(message = ExceptionConstants.FULLNAME_NOT_BLANK)
    @Size(max = 100, message = ExceptionConstants.FULLNAME_MAX_LENGTH)
    private String fullname;

    @Size(max = 45, message = ExceptionConstants.NAME_MAX_LENGTH)
    private String name;

    @NotBlank(message = ExceptionConstants.PHONE_NOT_BLANK)
    @Size(min = 6, message = ExceptionConstants.PHONE_MIN_LENGTH)
    @Size(max = 20, message = ExceptionConstants.PHONE_MAX_LENGTH)
    @Pattern(regexp = BaseConstants.REGEX.PHONE_PATTERN, message = ExceptionConstants.PHONE_INVALID)
    private String phone;

    @NotBlank(message = ExceptionConstants.EMAIL_NOT_BLANK)
    @Size(min = 6, message = ExceptionConstants.EMAIL_MIN_LENGTH)
    @Size(max = 100, message = ExceptionConstants.EMAIL_MAX_LENGTH)
    @Pattern(regexp = BaseConstants.REGEX.EMAIL_PATTERN, message = ExceptionConstants.EMAIL_INVALID)
    private String email;

    @NotNull(message = ExceptionConstants.ID_NOT_NULL)
    @Min(value = 1, message = ExceptionConstants.ID_INVALID)
    private Integer productId;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
