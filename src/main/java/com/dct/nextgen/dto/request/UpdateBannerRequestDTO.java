package com.dct.nextgen.dto.request;

import com.dct.nextgen.constants.ExceptionConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class UpdateBannerRequestDTO extends BaseRequestDTO {

    @Size(max = 45, message = ExceptionConstants.TEXT_STROKE_MAX_SIZE)
    private String textStroke1;

    @Size(max = 45, message = ExceptionConstants.TEXT_STROKE_MAX_SIZE)
    private String textStroke2;

    @Size(max = 45, message = ExceptionConstants.TEXT_UPPERCASE_MAX_SIZE)
    private String textUpperCase1;

    @Size(max = 45, message = ExceptionConstants.TEXT_UPPERCASE_MAX_SIZE)
    private String textUpperCase2;

    @NotBlank(message = ExceptionConstants.BANNER_POSITION_NOT_NULL)
    @Size(max = 45, message = ExceptionConstants.BANNER_POSITION_MAX_SIZE)
    private String position;

    private String image;
    private MultipartFile imageFile;

    public String getTextStroke1() {
        return textStroke1;
    }

    public void setTextStroke1(String textStroke1) {
        this.textStroke1 = textStroke1;
    }

    public String getTextStroke2() {
        return textStroke2;
    }

    public void setTextStroke2(String textStroke2) {
        this.textStroke2 = textStroke2;
    }

    public String getTextUpperCase1() {
        return textUpperCase1;
    }

    public void setTextUpperCase1(String textUpperCase1) {
        this.textUpperCase1 = textUpperCase1;
    }

    public String getTextUpperCase2() {
        return textUpperCase2;
    }

    public void setTextUpperCase2(String textUpperCase2) {
        this.textUpperCase2 = textUpperCase2;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }
}
