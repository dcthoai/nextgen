package com.dct.nextgen.entity;

import com.dct.base.entity.AbstractAuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "contact_form")
@DynamicUpdate
@SuppressWarnings("unused")
public class ContactForm extends AbstractAuditingEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = 1000, nullable = false)
    private String description;

    @Column(name = "description_2", length = 1000, nullable = false)
    private String description2;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }
}
