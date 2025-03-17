package com.dct.nextgen.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate // Hibernate only updates the changed columns to the database instead of updating the entire table
@Table(name = "permission")
public class Permission extends AbstractAuditingEntity {

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "code", nullable = false, length = 45, unique = true)
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "parent_ID")
    private Integer parentID;

    @Column(name = "parent_code", length = 45)
    private String parentCode;

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
