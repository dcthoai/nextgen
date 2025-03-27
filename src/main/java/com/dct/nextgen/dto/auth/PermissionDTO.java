package com.dct.nextgen.dto.auth;

import com.dct.nextgen.dto.mapping.IPermissionDTO;

public class PermissionDTO {

    private Integer id;
    private String name;
    private String code;
    private String description;
    private Integer parentId;
    private String parentCode;

    public PermissionDTO() {}

    public PermissionDTO(IPermissionDTO permissionDTO) {
        this.id = permissionDTO.getId();
        this.name = permissionDTO.getName();
        this.code = permissionDTO.getCode();
        this.description = permissionDTO.getDescription();
        this.parentId = permissionDTO.getParentId();
        this.parentCode = permissionDTO.getParentCode();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}
