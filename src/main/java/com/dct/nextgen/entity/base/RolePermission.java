package com.dct.nextgen.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate // Hibernate only updates the changed columns to the database instead of updating the entire table
@Table(name = "role_permission")
public class RolePermission extends AbstractAuditingEntity {

    @Column(name = "role_ID", nullable = false)
    private Integer roleID;

    @Column(name = "permission_ID", nullable = false)
    private Integer permissionID;

    @Column(name = "permission_code", nullable = false, length = 45)
    private String permissionCode;

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public Integer getPermissionID() {
        return permissionID;
    }

    public void setPermissionID(Integer permissionID) {
        this.permissionID = permissionID;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }
}
