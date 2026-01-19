package com.korber.inventory.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Id;

import java.time.LocalDate;


@Entity
@Table(name = "inventory_batch")
public class InventoryBatch {

    @Id
    @Column(name = "batch_id", nullable = false)
    private Long batchId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;

    public InventoryBatch() {}

    public InventoryBatch(
            Long batchId, Long productId, String productName, Integer quantity, LocalDate expiryDate) {
        this.batchId = batchId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}

