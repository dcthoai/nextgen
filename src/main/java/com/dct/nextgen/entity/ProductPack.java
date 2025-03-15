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
import java.util.List;

@Entity
@Table(name = "product_pack")
@DynamicUpdate
@SuppressWarnings("unused")
public class ProductPack extends AbstractAuditingEntity {

    @Column(name = "product_ID", nullable = false)
    private int productID;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "icon")
    private String icon;

    @Column(name = "price", nullable = false)
    private long price;

    @Column(name = "decription", length = 1000)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "pack_ID")
    @OrderColumn(name = "index")
    List<ProductPackDetail> packDetails = new ArrayList<>();

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductPackDetail> getPackDetails() {
        return packDetails;
    }

    public void setPackDetails(List<ProductPackDetail> packDetails) {
        this.packDetails = packDetails;
    }
}
