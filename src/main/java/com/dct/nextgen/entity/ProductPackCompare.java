package com.dct.nextgen.entity;

import com.dct.base.entity.AbstractAuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "product_pack_compare")
@DynamicUpdate
@SuppressWarnings("unused")
public class ProductPackCompare extends AbstractAuditingEntity {

    @Column(name = "pack_ID", nullable = false)
    private int packID;

    @Column(name = "product_ID", nullable = false)
    private int productID;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "content", nullable = false)
    private String content;

    public int getPackID() {
        return packID;
    }

    public void setPackID(int packID) {
        this.packID = packID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
