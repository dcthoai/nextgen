package com.dct.nextgen.entity;

import com.dct.nextgen.entity.base.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "project")
@DynamicInsert // Hibernate only insert the nonnull columns to the database instead of insert the entire table
@DynamicUpdate // Hibernate only updates the changed columns to the database instead of updating the entire table
@SuppressWarnings("unused")
public class Project extends AbstractAuditingEntity {

    @Column(name = "thumbnail_square", nullable = false)
    private String thumbnailSquare;

    @Column(name = "thumbnail_rect", nullable = false)
    private String thumbnailRect;

    @Column(name = "sub_name", length = 100, nullable = false)
    private String subName;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category_name", length = 100, nullable = false)
    private String categoryName;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = 1000, nullable = false)
    private String description;

    @Column(name = "more_description", length = 1000)
    private String moreDescription;

    @Column(name = "customer", length = 100, nullable = false)
    private String customer;

    @Column(name = "finished_date")
    private Instant finishedDate;

    @Column(name = "link")
    private String linkDemo;

    @Column(name = "link_title", length = 100)
    private String linkDemoTitle;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderColumn(name = "position")
    @JsonManagedReference
    private List<ProjectImage> images = new ArrayList<>();

    public String getThumbnailSquare() {
        return thumbnailSquare;
    }

    public void setThumbnailSquare(String thumbnailSquare) {
        this.thumbnailSquare = thumbnailSquare;
    }

    public String getThumbnailRect() {
        return thumbnailRect;
    }

    public void setThumbnailRect(String thumbnailRect) {
        this.thumbnailRect = thumbnailRect;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

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

    public String getMoreDescription() {
        return moreDescription;
    }

    public void setMoreDescription(String moreDescription) {
        this.moreDescription = moreDescription;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Instant getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Instant finishedDate) {
        this.finishedDate = finishedDate;
    }

    public String getLinkDemo() {
        return linkDemo;
    }

    public void setLinkDemo(String linkDemo) {
        this.linkDemo = linkDemo;
    }

    public String getLinkDemoTitle() {
        return linkDemoTitle;
    }

    public void setLinkDemoTitle(String linkDemoTitle) {
        this.linkDemoTitle = linkDemoTitle;
    }

    public List<ProjectImage> getImages() {
        return images;
    }

    public void setImages(List<ProjectImage> images) {
        this.images = images;
    }
}
