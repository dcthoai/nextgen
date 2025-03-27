package com.dct.nextgen.dto.auth;

import com.dct.nextgen.dto.mapping.IRoleDTO;
import jakarta.persistence.Tuple;

import java.util.ArrayList;
import java.util.List;

public class RoleDTO {

    private Integer id;
    private String name;
    private String code;
    List<PermissionDTO> rolePermissions = new ArrayList<>();

    public RoleDTO() {}

    public RoleDTO(IRoleDTO roleDTO) {
        this.id = roleDTO.getId();
        this.name = roleDTO.getName();
        this.code = roleDTO.getCode();
    }

    public RoleDTO(Tuple tuple) {
        this.id = tuple.get("id", Integer.class);
        this.name = tuple.get("name", String.class);
        this.code = tuple.get("code", String.class);
    }

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

    public List<PermissionDTO> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(List<PermissionDTO> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }
}
