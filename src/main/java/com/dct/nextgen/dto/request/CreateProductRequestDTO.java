package com.dct.nextgen.dto.request;

import com.dct.nextgen.constants.ExceptionConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class CreateProductRequestDTO {

    @NotBlank(message = ExceptionConstants.NAME_NOT_BLANK)
    @Size(max = 255, message = ExceptionConstants.NAME_MAX_LENGTH)
    private String name;

    @Size(max = 1000, message = ExceptionConstants.DESCRIPTION_MAX_LENGTH)
    private String description;

    @Size(min = 1, message = ExceptionConstants.CAROUSEL_NOT_EMPTY)
    private MultipartFile[] carouselFiles;

    @Size(min = 1, message = ExceptionConstants.PRODUCT_INTRO_NOT_EMPTY)
    private List<String> intros = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile[] getCarouselFiles() {
        return carouselFiles;
    }

    public void setCarouselFiles(MultipartFile[] carouselFiles) {
        this.carouselFiles = carouselFiles;
    }

    public List<String> getIntros() {
        return intros;
    }

    public void setIntros(List<String> intros) {
        this.intros = intros;
    }
}
