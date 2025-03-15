package com.dct.nextgen.entity;

import com.dct.base.entity.AbstractAuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

import java.time.Instant;

@Entity
@Table(name = "company")
@DynamicUpdate
@SuppressWarnings("unused")
public class Company extends AbstractAuditingEntity {

    @Column(name = "logo", nullable = false)
    private String logo;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "video_intro")
    private String videoIntro;

    @Column(name = "license_code", length = 20)
    private String licenseCode;

    @Column(name = "license_date")
    private Instant licenseDate;

    @Column(name = "license_address")
    private String licenseAddress;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "description", length = 1000, nullable = false)
    private String description;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone", length = 20, nullable = false)
    private String phone;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "website", length = 100, nullable = false)
    private String website;

    @Column(name = "map", length = 2500, nullable = false)
    private String map;

    @Column(name = "map_image", nullable = false)
    private String mapImage;

    @Column(name = "map_slide_text", length = 25, nullable = false)
    private String mapSlideText;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "instagram")
    private String instagram;

    @Column(name = "zalo")
    private String zalo;

    @Column(name = "youtube")
    private String youtube;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideoIntro() {
        return videoIntro;
    }

    public void setVideoIntro(String videoIntro) {
        this.videoIntro = videoIntro;
    }

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode;
    }

    public Instant getLicenseDate() {
        return licenseDate;
    }

    public void setLicenseDate(Instant licenseDate) {
        this.licenseDate = licenseDate;
    }

    public String getLicenseAddress() {
        return licenseAddress;
    }

    public void setLicenseAddress(String licenseAddress) {
        this.licenseAddress = licenseAddress;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getMapImage() {
        return mapImage;
    }

    public void setMapImage(String mapImage) {
        this.mapImage = mapImage;
    }

    public String getMapSlideText() {
        return mapSlideText;
    }

    public void setMapSlideText(String mapSlideText) {
        this.mapSlideText = mapSlideText;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getZalo() {
        return zalo;
    }

    public void setZalo(String zalo) {
        this.zalo = zalo;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }
}
