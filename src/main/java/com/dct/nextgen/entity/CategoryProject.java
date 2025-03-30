package com.dct.nextgen.entity;

import com.dct.nextgen.entity.base.AbstractAuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "category_project")
@DynamicUpdate
@SuppressWarnings("unused")
public class CategoryProject extends AbstractAuditingEntity {

    @Column(name = "project_id", nullable = false)
    private Integer projectId;

    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
