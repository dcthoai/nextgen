package com.dct.nextgen.entity;

import com.dct.base.entity.AbstractAuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "story_image")
@DynamicUpdate
@SuppressWarnings("unused")
public class StoryImage extends AbstractAuditingEntity {

    @Column(name = "story_ID", nullable = false)
    private int storyID;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "index", nullable = false)
    private int index;

    public int getStoryID() {
        return storyID;
    }

    public void setStoryID(int storyID) {
        this.storyID = storyID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
