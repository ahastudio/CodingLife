package com.ahastudio.demo.shop.models;

import java.time.LocalDateTime;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Builder;

@Entity
@Table(name = "shops")
public class Shop {
    @EmbeddedId
    private ShopId id;

    private String name;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private Shop() {
    }

    @Builder
    public Shop(ShopId id, String name) {
        this.id = id;
        this.name = name;
    }
}
