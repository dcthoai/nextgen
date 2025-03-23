package com.dct.nextgen.dto.request;

import com.dct.nextgen.dto.auth.PermissionDTO;

import java.util.ArrayList;
import java.util.List;

public class CreateRoleRequestDTO {

    private String name;
    private String code;
    private List<PermissionDTO> permissions;

    public CreateRoleRequestDTO() {
        permissions = new ArrayList<>();
    }

    public CreateRoleRequestDTO(String name, String code, List<PermissionDTO> permissions) {
        this.name = name;
        this.code = code;
        this.permissions = permissions;
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

    public List<PermissionDTO> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionDTO> permissions) {
        this.permissions = permissions;
    }
}
