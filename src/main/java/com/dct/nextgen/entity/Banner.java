package com.dct.nextgen.entity;

import com.dct.base.entity.AbstractAuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "banner")
@DynamicUpdate
@SuppressWarnings("unused")
public class Banner extends AbstractAuditingEntity {

    @Column(name = "text_stroke_1", length = 45)
    private String textStroke1;

    @Column(name = "text_stroke_2", length = 45)
    private String textStroke2;

    @Column(name = "text_uppercase_1", length = 45)
    private String textUpperCase1;

    @Column(name = "text_uppercase_2", length = 45)
    private String textUpperCase2;

    @Column(name = "image")
    private String image;

    @Column(name = "position", length = 45, nullable = false, unique = true)
    private String position;

    public Banner() {}

    public Banner(String position) {
        this.position = position;
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
