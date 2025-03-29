package com.dct.nextgen.dto.about;

import com.dct.nextgen.dto.response.AuditingEntityDTO;

public class PartnerDTO extends AuditingEntityDTO {

    private Integer id;
    private String name;
    private String logo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
