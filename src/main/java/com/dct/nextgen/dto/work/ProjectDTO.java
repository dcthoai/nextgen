package com.dct.nextgen.dto.work;

import com.dct.nextgen.dto.mapping.ICategoryDTO;
import com.dct.nextgen.dto.mapping.IProjectImageDTO;
import com.dct.nextgen.dto.response.AuditingEntityDTO;

import java.util.ArrayList;
import java.util.List;

public class ProjectDTO extends AuditingEntityDTO {

    private Integer id;
    private String thumbnailSquare;
    private String thumbnailRect;
    private String subName;
    private String name;
    private String categoryName;
    private String title;
    private String description;
    private String moreDescription;
    private String customer;
    private String finishedDate;
    private String linkDemo;
    private String linkDemoTitle;
    private List<ICategoryDTO> categories = new ArrayList<>();
    private List<IProjectImageDTO> projectImages = new ArrayList<>();
    private List<String> imagesUrls = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(String finishedDate) {
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

    public List<ICategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<ICategoryDTO> categories) {
        this.categories = categories;
    }

    public List<IProjectImageDTO> getProjectImages() {
        return projectImages;
    }

    public void setProjectImages(List<IProjectImageDTO> projectImages) {
        this.projectImages = projectImages;
    }

    public List<String> getImagesUrls() {
        return imagesUrls;
    }

    public void setImagesUrls(List<String> imagesUrls) {
        this.imagesUrls = imagesUrls;
    }
}
