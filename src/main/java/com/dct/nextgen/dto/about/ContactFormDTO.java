package com.dct.nextgen.dto.about;

import com.dct.nextgen.dto.response.AuditingEntityDTO;

public class ContactFormDTO extends AuditingEntityDTO {

    private Integer id;
    private String title;
    private String text1;
    private String text2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
