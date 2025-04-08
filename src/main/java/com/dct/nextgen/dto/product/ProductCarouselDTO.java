package com.dct.nextgen.dto.product;

import org.springframework.web.multipart.MultipartFile;

public class ProductCarouselDTO {

    private Integer id;
    private String url;
    private MultipartFile image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
