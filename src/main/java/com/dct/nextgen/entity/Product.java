package com.dct.nextgen.entity;

import com.dct.nextgen.entity.base.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
@DynamicInsert // Hibernate only insert the nonnull columns to the database instead of insert the entire table
@DynamicUpdate // Hibernate only updates the changed columns to the database instead of updating the entire table
@SuppressWarnings("unused")
public class Product extends AbstractAuditingEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private Set<ProductCarousel> productCarousels = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    @OrderColumn(name = "position")
    private List<ProductIntro> productIntros = new ArrayList<>();

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

    public List<ProductIntro> getProductIntros() {
        return productIntros;
    }

    public void setProductIntros(List<ProductIntro> productIntros) {
        this.productIntros = productIntros;
    }
}
