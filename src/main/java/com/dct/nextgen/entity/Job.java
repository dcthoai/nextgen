package com.dct.nextgen.entity;

import com.dct.base.entity.AbstractAuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "job")
@DynamicUpdate
@SuppressWarnings("unused")
public class Job extends AbstractAuditingEntity {

    @Column(name = "name", length = 300, nullable = false)
    private String name;

    @Column(name = "description", length = 1500, nullable = false)
    private String description;

    @Column(name = "contact_mail", length = 100, nullable = false)
    private String contactMail;

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

    public String getContactMail() {
        return contactMail;
    }

    public void setContactMail(String contactMail) {
        this.contactMail = contactMail;
    }
}
