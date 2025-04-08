package com.dct.nextgen.dto.product;

import com.dct.nextgen.dto.mapping.IProductCarouselDTO;
import com.dct.nextgen.dto.mapping.IProductIntroDTO;
import com.dct.nextgen.dto.response.AuditingEntityDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductDTO extends AuditingEntityDTO {

    private Integer id;
    private String name;
    private String description;
    private Set<IProductCarouselDTO> carousels = new HashSet<>();
    private Set<String> carouselUrls = new HashSet<>();
    private List<IProductIntroDTO> productIntros = new ArrayList<>();
    private List<String> intros = new ArrayList<>();

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

    public Set<String> getCarouselUrls() {
        return carouselUrls;
    }

    public void setCarouselUrls(Set<String> carouselUrls) {
        this.carouselUrls = carouselUrls;
    }

    public List<String> getIntros() {
        return intros;
    }

    public void setIntros(List<String> intros) {
        this.intros = intros;
    }

    public Set<IProductCarouselDTO> getCarousels() {
        return carousels;
    }

    public void setCarousels(Set<IProductCarouselDTO> carousels) {
        this.carousels = carousels;
    }

    public List<IProductIntroDTO> getProductIntros() {
        return productIntros;
    }

    public void setProductIntros(List<IProductIntroDTO> productIntros) {
        this.productIntros = productIntros;
    }
}
