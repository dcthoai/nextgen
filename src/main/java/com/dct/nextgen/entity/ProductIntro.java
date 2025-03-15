package com.dct.nextgen.entity;

import com.dct.base.entity.AbstractAuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "product_intro")
@DynamicUpdate
@SuppressWarnings("unused")
public class ProductIntro extends AbstractAuditingEntity {

    @Column(name = "product_ID", nullable = false)
    private int productID;

    @Column(name = "content")
    private String content;

    @Column(name = "index")
    private int index;

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
