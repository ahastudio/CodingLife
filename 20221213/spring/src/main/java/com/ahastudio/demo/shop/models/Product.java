package com.ahastudio.demo.shop.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Builder;

@Entity
@Table(name = "products")
public class Product {
    @EmbeddedId
    @Column(name = "id")
    private ProductId id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Money price;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private Product() {
    }

    @Builder
    public Product(ProductId id, String name, Money price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public ProductId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Money price() {
        return price;
    }
}
