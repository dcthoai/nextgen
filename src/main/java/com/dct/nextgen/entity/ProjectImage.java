package com.dct.nextgen.entity;

import com.dct.base.entity.AbstractAuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "project_image")
@DynamicUpdate
@SuppressWarnings("unused")
public class ProjectImage extends AbstractAuditingEntity {

    @Column(name = "project_ID")
    private int projectID;

    @Column(name = "index", nullable = false)
    private int index;

    @Column(name = "url", nullable = false)
    private String url;

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
