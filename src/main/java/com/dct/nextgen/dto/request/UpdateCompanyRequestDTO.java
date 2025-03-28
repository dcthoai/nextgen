package com.dct.nextgen.dto.request;

import com.dct.nextgen.constants.BaseConstants;
import com.dct.nextgen.constants.ExceptionConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class UpdateCompanyRequestDTO extends BaseRequestDTO {

    @NotBlank(message = ExceptionConstants.LOGO_NOT_NULL)
    private String logo;

    @NotBlank(message = ExceptionConstants.IMAGE_NOT_NULL)
    private String image;

    private String videoIntro;

    @Size(max = 20, message = ExceptionConstants.LICENSE_CODE_MAX_SIZE)
    private String licenseCode;

    private String licenseDate;
    private String licenseAddress;

    @NotBlank(message = ExceptionConstants.NAME_NOT_BLANK)
    @Size(max = 100, message = ExceptionConstants.NAME_MAX_LENGTH)
    private String name;

    @Size(max = 500, message = ExceptionConstants.DESCRIPTION_MAX_LENGTH)
    private String description;

    @NotBlank(message = ExceptionConstants.EMAIL_NOT_BLANK)
    @Size(min = 6, message = ExceptionConstants.EMAIL_MIN_LENGTH)
    @Size(max = 100, message = ExceptionConstants.EMAIL_MAX_LENGTH)
    @Pattern(regexp = BaseConstants.REGEX.EMAIL_PATTERN, message = ExceptionConstants.EMAIL_INVALID)
    private String email;

    @NotBlank(message = ExceptionConstants.PHONE_NOT_BLANK)
    @Size(min = 6, message = ExceptionConstants.PHONE_MIN_LENGTH)
    @Size(max = 20, message = ExceptionConstants.PHONE_MAX_LENGTH)
    @Pattern(regexp = BaseConstants.REGEX.PHONE_PATTERN, message = ExceptionConstants.PHONE_INVALID)
    private String phone;

    @NotBlank(message = ExceptionConstants.ADDRESS_NOT_BLANK)
    @Size(max = 255, message = ExceptionConstants.ADDRESS_MAX_LENGTH)
    private String address;

    @Size(max = 100, message = ExceptionConstants.WEBSITE_MAX_SIZE)
    private String website;

    @NotBlank(message = ExceptionConstants.COMPANY_MAP_NOT_NULL)
    @Size(max = 3000, message = ExceptionConstants.COMPANY_MAP_MAX_SIZE)
    private String map;

    @NotBlank(message = ExceptionConstants.COMPANY_MAP_IMAGE_NOT_NULL)
    private String mapImage;

    @Size(max = 100, message = ExceptionConstants.COMPANY_MAP_SLIDE_TEXT_MAX_SIZE)
    private String mapSlideText;

    private String facebook;
    private String instagram;
    private String zalo;
    private String youtube;
    private MultipartFile logoFile;
    private MultipartFile imageFile;
    private MultipartFile mapImageFile;

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

    public String getLicenseDate() {
        return licenseDate;
    }

    public void setLicenseDate(String licenseDate) {
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

    public MultipartFile getLogoFile() {
        return logoFile;
    }

    public void setLogoFile(MultipartFile logoFile) {
        this.logoFile = logoFile;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    public MultipartFile getMapImageFile() {
        return mapImageFile;
    }

    public void setMapImageFile(MultipartFile mapImageFile) {
        this.mapImageFile = mapImageFile;
    }
}
