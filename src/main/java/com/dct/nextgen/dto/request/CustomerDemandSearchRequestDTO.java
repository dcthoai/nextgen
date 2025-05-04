package com.dct.nextgen.dto.request;

import com.dct.nextgen.constants.BaseConstants;
import com.dct.nextgen.constants.ExceptionConstants;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CustomerDemandSearchRequestDTO extends BaseRequestDTO {

    @Min(value = 1, message = ExceptionConstants.ID_INVALID)
    private Integer productId;

    @Size(min = 6, message = ExceptionConstants.PHONE_MIN_LENGTH)
    @Size(max = 20, message = ExceptionConstants.PHONE_MAX_LENGTH)
    @Pattern(regexp = BaseConstants.REGEX.PHONE_PATTERN, message = ExceptionConstants.PHONE_INVALID)
    private String phone;

    @Size(min = 6, message = ExceptionConstants.EMAIL_MIN_LENGTH)
    @Size(max = 100, message = ExceptionConstants.EMAIL_MAX_LENGTH)
    @Pattern(regexp = BaseConstants.REGEX.EMAIL_PATTERN, message = ExceptionConstants.EMAIL_INVALID)
    private String email;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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
}
