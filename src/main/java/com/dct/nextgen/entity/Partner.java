package com.dct.nextgen.entity;

import com.dct.base.entity.AbstractAuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "partner")
@DynamicUpdate
@SuppressWarnings("unused")
public class Partner extends AbstractAuditingEntity {

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "logo", nullable = false)
    private String logo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
