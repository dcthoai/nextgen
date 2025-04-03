package com.dct.nextgen.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class UpdateProjectRequestDTO {

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
    private MultipartFile thumbnailSquareFile;
    private MultipartFile thumbnailRectFile;

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

    public MultipartFile getThumbnailSquareFile() {
        return thumbnailSquareFile;
    }

    public void setThumbnailSquareFile(MultipartFile thumbnailSquareFile) {
        this.thumbnailSquareFile = thumbnailSquareFile;
    }

    public MultipartFile getThumbnailRectFile() {
        return thumbnailRectFile;
    }

    public void setThumbnailRectFile(MultipartFile thumbnailRectFile) {
        this.thumbnailRectFile = thumbnailRectFile;
    }
}
