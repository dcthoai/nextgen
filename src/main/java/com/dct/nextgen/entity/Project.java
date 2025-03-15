package com.dct.nextgen.entity;

import com.dct.base.entity.AbstractAuditingEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "project")
@DynamicUpdate
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

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "title")
    private String title;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "description_2", length = 1000)
    private String description2;

    @Column(name = "customer", nullable = false)
    private String customer;

    @Column(name = "finished_date", nullable = false)
    private Instant finishedDate;

    @Column(name = "link", nullable = false)
    private String linkDemo;

    @Column(name = "link_title", length = 100, nullable = false)
    private String linkDemoTitle;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "project_ID")
    @OrderColumn(name = "index")
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
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
