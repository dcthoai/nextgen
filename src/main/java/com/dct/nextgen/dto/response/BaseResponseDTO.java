package com.dct.nextgen.dto.response;

import com.dct.nextgen.constants.HttpStatusConstants;
import com.dct.nextgen.constants.ResultConstants;

import java.util.Objects;

/**
 * The format helps standardize the response for the client <p>
 * Other response types can inherit from it and extend it as needed for specific cases <p>
 * Normally only need this class for responses
 *
 * @author thoaidc
 */
@SuppressWarnings("unused")
public class BaseResponseDTO {

    private Integer code; // Http status

    // The status indicating successful processing of the request (in the case of valid input data and no system errors)
    private Boolean status;
    private String message; // The response content follows the i18n standard
    private Object result; // The data after processing the request, is not required and can be null
    private Long total; // Total records if query with pageable, is not required and can be null

    public static Builder builder() {
        return new Builder();
    }

    // The builder allows for faster response creation
    public static class Builder {
        private final BaseResponseDTO instance = BaseResponseDTO.builder().ok();

        public Builder code(int code) {
            instance.code = code;
            return this;
        }

        public Builder success(boolean status) {
            instance.status = status;
            return this;
        }

        public Builder message(String message) {
            instance.message = message;
            return this;
        }

        public Builder result(Object result) {
            instance.result = result;
            return this;
        }

        public Builder total(Long totalRecords) {
            instance.total = totalRecords;
            return this;
        }

        public BaseResponseDTO ok() {
            instance.code = HttpStatusConstants.OK;
            instance.status = HttpStatusConstants.STATUS.SUCCESS;
            instance.message = ResultConstants.SUCCESS;
            instance.total = 0L;
            return instance;
        }

        public BaseResponseDTO ok(Object result) {
            instance.result = result;
            return ok();
        }

        public BaseResponseDTO build() {
            if (Objects.isNull(instance.code) || Objects.isNull(instance.status))
                return ok();

            return instance;
        }
    }

    private BaseResponseDTO() {}

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
