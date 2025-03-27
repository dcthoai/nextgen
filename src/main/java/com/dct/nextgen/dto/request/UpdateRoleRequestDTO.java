package com.dct.nextgen.dto.request;

import com.dct.nextgen.constants.ExceptionConstants;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class UpdateRoleRequestDTO extends BaseRequestDTO {

    @Min(value = 1, message = ExceptionConstants.ROLE_ID_INVALID)
    private Integer id;

    @NotBlank(message = ExceptionConstants.ROLE_NAME_NOT_NULL)
    private String name;

    @NotBlank(message = ExceptionConstants.ROLE_CODE_NOT_NULL)
    private String code;

    @Size(min = 1, message = ExceptionConstants.ROLE_PERMISSIONS_NOT_EMPTY)
    private List<Integer> permissionIDs = new ArrayList<>();

    public UpdateRoleRequestDTO() {}

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Integer> getPermissionIDs() {
        return permissionIDs;
    }

    public void setPermissionIDs(List<Integer> permissionIDs) {
        this.permissionIDs = permissionIDs;
    }
}
