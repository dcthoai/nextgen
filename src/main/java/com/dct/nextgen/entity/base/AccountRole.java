package com.dct.nextgen.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate // Hibernate only updates the changed columns to the database instead of updating the entire table
@Table(name = "account_role")
@SuppressWarnings("unused")
public class AccountRole extends AbstractAuditingEntity {

    @Column(name = "account_ID", nullable = false)
    private Integer accountID;

    @Column(name = "role_ID", nullable = false)
    private Integer roleID;

    public AccountRole() {}

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }
}
