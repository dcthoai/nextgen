package com.dct.nextgen.entity;

import com.dct.nextgen.entity.base.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "product_intro")
@DynamicInsert // Hibernate only insert the nonnull columns to the database instead of insert the entire table
@DynamicUpdate // Hibernate only updates the changed columns to the database instead of updating the entire table
@SuppressWarnings("unused")
public class ProductIntro extends AbstractAuditingEntity {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id", updatable = false, nullable = false)
    @JsonIgnore
    private Product product;

    @Column(name = "content")
    private String content;

    @Column(name = "position")
    private int position;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
