package com.dct.nextgen.dto.request;

import com.dct.nextgen.constants.ExceptionConstants;
import com.dct.nextgen.dto.product.ProductCarouselDTO;
import com.dct.nextgen.dto.product.ProductIntroDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class UpdateProductRequestDTO {

    @NotNull(message = ExceptionConstants.ID_NOT_NULL)
    @Min(value = 1, message = ExceptionConstants.ID_INVALID)
    private Integer id;

    @NotBlank(message = ExceptionConstants.NAME_NOT_BLANK)
    @Size(max = 255, message = ExceptionConstants.NAME_MAX_LENGTH)
    private String name;

    @Size(max = 1000, message = ExceptionConstants.DESCRIPTION_MAX_LENGTH)
    private String description;

    @Size(min = 1, message = ExceptionConstants.CAROUSEL_NOT_EMPTY)
    private List<ProductCarouselDTO> carousels = new ArrayList<>();

    @Size(min = 1, message = ExceptionConstants.PRODUCT_INTRO_NOT_EMPTY)
    private List<ProductIntroDTO> intros = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public List<ProductCarouselDTO> getCarousels() {
        return carousels;
    }

    public void setCarousels(List<ProductCarouselDTO> carousels) {
        this.carousels = carousels;
    }

    public List<ProductIntroDTO> getIntros() {
        return intros;
    }

    public void setIntros(List<ProductIntroDTO> intros) {
        this.intros = intros;
    }
}
