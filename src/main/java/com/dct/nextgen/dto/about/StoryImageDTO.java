package com.dct.nextgen.dto.about;

import com.dct.nextgen.dto.response.AuditingEntityDTO;
import org.springframework.web.multipart.MultipartFile;

public class StoryImageDTO extends AuditingEntityDTO {

    private Integer id;
    private Integer storyId;
    private String url;
    private Integer position;
    private MultipartFile image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoryId() {
        return storyId;
    }

    public void setStoryId(Integer storyId) {
        this.storyId = storyId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
