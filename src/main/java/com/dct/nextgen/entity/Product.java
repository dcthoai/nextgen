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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
@DynamicUpdate
@SuppressWarnings("unused")
public class Product extends AbstractAuditingEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "product_ID")
    private Set<ProductCarousel> productCarousels = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "product_ID")
    @OrderColumn(name = "index")
    private List<ProductIntro> productInfos = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ProductCarousel> getProductCarousels() {
        return productCarousels;
    }

    public void setProductCarousels(Set<ProductCarousel> productCarousels) {
        this.productCarousels = productCarousels;
    }

    public List<ProductIntro> getProductInfos() {
        return productInfos;
    }

    public void setProductInfos(List<ProductIntro> productInfos) {
        this.productInfos = productInfos;
    }
}
