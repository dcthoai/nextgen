package com.dct.nextgen.dto.auth;

import com.dct.nextgen.dto.mapping.IRoleDTO;
import jakarta.persistence.Tuple;

import java.util.ArrayList;
import java.util.List;

public class RoleDTO {

    private Integer ID;
    private String name;
    private String code;
    List<PermissionDTO> rolePermissions = new ArrayList<>();

    public RoleDTO() {}

    public RoleDTO(IRoleDTO roleDTO) {
        this.ID = roleDTO.getID();
        this.name = roleDTO.getName();
        this.code = roleDTO.getCode();
    }

    public RoleDTO(Tuple tuple) {
        this.ID = tuple.get("id", Integer.class);
        this.name = tuple.get("name", String.class);
        this.code = tuple.get("code", String.class);
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
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

    public List<PermissionDTO> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(List<PermissionDTO> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }
}
