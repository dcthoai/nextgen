package com.dct.nextgen.entity;

import com.dct.base.entity.AbstractAuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "product_pack_detail")
@DynamicUpdate
@SuppressWarnings("unused")
public class ProductPackDetail extends AbstractAuditingEntity {

    @Column(name = "pack_ID", nullable = false)
    private int packID;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "index", nullable = false)
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPackID() {
        return packID;
    }

    public void setPackID(int packID) {
        this.packID = packID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
