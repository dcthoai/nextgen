package com.dct.nextgen.entity;

import com.dct.base.entity.AbstractAuditingEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "story")
@DynamicUpdate
@SuppressWarnings("unused")
public class Story extends AbstractAuditingEntity {

    @Column(name = "subtitle", length = 150, nullable = false)
    private String subtitle;

    @Column(name = "title", length = 400, nullable = false)
    private String title;

    @Column(name = "text1", length = 1000)
    private String text1;

    @Column(name = "text2", length = 1000)
    private String text2;

    @Column(name = "text3", length = 1000)
    private String text3;

    @Column(name = "position", length = 50, nullable = false, unique = true)
    private String position;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "story_ID")
    @OrderColumn(name = "index")
    private List<StoryImage> images = new ArrayList<>();

    public Story() {}

    public Story(String position) {
        this.position = position;
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

    public List<StoryImage> getImages() {
        return images;
    }

    public void setImages(List<StoryImage> images) {
        this.images = images;
    }
}
