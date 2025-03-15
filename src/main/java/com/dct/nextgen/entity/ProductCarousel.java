package com.dct.nextgen.entity;

import com.dct.base.entity.AbstractAuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "product_carousel")
@DynamicUpdate
@SuppressWarnings("unused")
public class ProductCarousel extends AbstractAuditingEntity {

    @Column(name = "product_ID", nullable = false)
    private int productID;

    @Column(name = "url", nullable = false)
    private String url;

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
