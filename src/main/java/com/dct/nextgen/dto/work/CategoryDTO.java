package com.dct.nextgen.dto.work;

import com.dct.nextgen.dto.response.AuditingEntityDTO;

public class CategoryDTO extends AuditingEntityDTO {

    private Integer id;
    private String name;
    private long quantity;

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

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
