package com.dct.nextgen.entity;

import com.dct.base.entity.AbstractAuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "motto")
@DynamicUpdate
@SuppressWarnings("unused")
public class Motto extends AbstractAuditingEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 1000, nullable = false)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
