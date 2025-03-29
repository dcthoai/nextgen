package com.dct.nextgen.dto.request;

import com.dct.nextgen.dto.about.StoryImageDTO;

import java.util.ArrayList;
import java.util.List;

public class UpdateStoryRequestDTO extends BaseRequestDTO {

    private Integer id;
    private String subtitle;
    private String title;
    private String text1;
    private String text2;
    private String text3;
    private String position;
    private List<StoryImageDTO> storyImages = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<StoryImageDTO> getStoryImages() {
        return storyImages;
    }

    public void setStoryImages(List<StoryImageDTO> storyImages) {
        this.storyImages = storyImages;
    }
}
