package com.example.demo.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "product_option_items")
public class ProductOptionItem {
    @EmbeddedId
    private ProductOptionItemId id;

    @Column(name = "name")
    private String name;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private ProductOptionItem() {
    }

    public ProductOptionItem(ProductOptionItemId id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductOptionItemId id() {
        return id;
    }

    public String name() {
        return name;
    }
}
