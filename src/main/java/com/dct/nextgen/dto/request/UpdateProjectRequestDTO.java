package com.dct.nextgen.dto.request;

import com.dct.nextgen.constants.ExceptionConstants;
import com.dct.nextgen.dto.work.ProjectImageDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class UpdateProjectRequestDTO {

    @NotNull(message = ExceptionConstants.ID_NOT_NULL)
    @Min(value = 1, message = ExceptionConstants.ID_INVALID)
    private Integer id;

    @NotBlank(message = ExceptionConstants.SUB_NAME_NOT_BLANK)
    @Size(max = 100, message = ExceptionConstants.SUB_NAME_MAX_LENGTH)
    private String subName;

    @NotBlank(message = ExceptionConstants.NAME_NOT_BLANK)
    @Size(max = 255, message = ExceptionConstants.NAME_MAX_LENGTH)
    private String name;

    @NotBlank(message = ExceptionConstants.CATEGORY_NAME_NOT_BLANK)
    @Size(max = 100, message = ExceptionConstants.CATEGORY_NAME_MAX_LENGTH)
    private String categoryName;

    @NotBlank(message = ExceptionConstants.TITLE_NOT_BLANK)
    @Size(max = 255, message = ExceptionConstants.TITLE_MAX_LENGTH)
    private String title;

    @NotBlank(message = ExceptionConstants.DESCRIPTION_NOT_BLANK)
    @Size(max = 1000, message = ExceptionConstants.DESCRIPTION_MAX_LENGTH)
    private String description;

    @Size(max = 1000, message = ExceptionConstants.MORE_DESCRIPTION_MAX_LENGTH)
    private String moreDescription;

    @NotBlank(message = ExceptionConstants.CUSTOMER_NAME_NOT_BLANK)
    @Size(max = 100, message = ExceptionConstants.CUSTOMER_NAME_MAX_LENGTH)
    private String customer;

    private String finishedDate;
    private String linkDemo;

    @Size(max = 100, message = ExceptionConstants.LINK_NAME_MAX_LENGTH)
    private String linkDemoTitle;

    private List<Integer> categoryIds = new ArrayList<>();
    private List<ProjectImageDTO> projectImageDTOs = new ArrayList<>();
    private MultipartFile thumbnailSquareFile;
    private MultipartFile thumbnailRectFile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
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

    public List<ProjectImageDTO> getProjectImageDTOs() {
        return projectImageDTOs;
    }

    public void setProjectImageDTOs(List<ProjectImageDTO> projectImageDTOs) {
        this.projectImageDTOs = projectImageDTOs;
    }
}
