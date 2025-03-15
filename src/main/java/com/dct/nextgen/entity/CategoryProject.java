package com.dct.nextgen.entity;

import com.dct.base.entity.AbstractAuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "category_project")
@DynamicUpdate
@SuppressWarnings("unused")
public class CategoryProject extends AbstractAuditingEntity {

    @Column(name = "project_ID", nullable = false)
    private int projectID;

    @Column(name = "category_ID", nullable = false)
    private int categoryID;

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
}
