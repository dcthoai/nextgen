package com.dct.nextgen.dto.request;

import com.dct.nextgen.constants.ExceptionConstants;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateOrUpdateCategoryRequestDTO {

    @Min(value = 1, message = ExceptionConstants.ID_INVALID)
    private Integer id;

    @NotBlank(message = ExceptionConstants.NAME_NOT_BLANK)
    @Size(max = 150, message = ExceptionConstants.NAME_MAX_LENGTH)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
