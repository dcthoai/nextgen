package com.dct.nextgen.dto.request;

import com.dct.nextgen.constants.ExceptionConstants;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateContactFormRequestDTO extends BaseRequestDTO {

    @Min(value = 1, message = ExceptionConstants.ID_INVALID)
    private Integer id;

    @NotBlank(message = ExceptionConstants.TITLE_NOT_BLANK)
    @Size(max = 255, message = ExceptionConstants.TITLE_MAX_LENGTH)
    private String title;

    @Size(max = 500, message = ExceptionConstants.CONTENT_MAX_LENGTH)
    private String text1;

    @Size(max = 500, message = ExceptionConstants.CONTENT_MAX_LENGTH)
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
