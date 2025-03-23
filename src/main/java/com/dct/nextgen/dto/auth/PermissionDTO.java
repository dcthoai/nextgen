package com.dct.nextgen.dto.auth;

import com.dct.nextgen.dto.mapping.IPermissionDTO;

public class PermissionDTO {

    private Integer ID;
    private String name;
    private String code;
    private String description;
    private Integer parentID;
    private String parentCode;

    public PermissionDTO() {}

    public PermissionDTO(IPermissionDTO permissionDTO) {
        this.ID = permissionDTO.getId();
        this.name = permissionDTO.getName();
        this.code = permissionDTO.getCode();
        this.description = permissionDTO.getDescription();
        this.parentID = permissionDTO.getParentId();
        this.parentCode = permissionDTO.getParentCode();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getParentID() {
        return parentID;
    }

    public void setParentID(Integer parentID) {
        this.parentID = parentID;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}
