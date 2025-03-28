package com.dct.nextgen.dto.company;

import com.dct.nextgen.dto.response.AuditingEntityDTO;

public class BannerDTO extends AuditingEntityDTO {

    private Integer id;
    private String textStroke1;
    private String textStroke2;
    private String textUpperCase1;
    private String textUpperCase2;
    private String image;
    private String position;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
