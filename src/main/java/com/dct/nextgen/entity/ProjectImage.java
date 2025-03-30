package com.dct.nextgen.entity;

import com.dct.nextgen.entity.base.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "project_image")
@DynamicUpdate
@SuppressWarnings("unused")
public class ProjectImage extends AbstractAuditingEntity {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id", referencedColumnName = "id", updatable = false, nullable = false)
    @JsonBackReference
    private Project project;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "position", nullable = false)
    private Integer position;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
