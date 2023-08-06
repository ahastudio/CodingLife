package com.example.demo.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "categories")
public class Category {
    @EmbeddedId
    private CategoryId id;

    @Column(name = "name")
    private String name;

    @Column(name = "hidden")
    private boolean hidden;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private Category() {
    }

    public Category(CategoryId id, String name, boolean hidden) {
        this.id = id;
        this.name = name;
        this.hidden = hidden;
    }

    public Category(CategoryId id, String name) {
        this(id, name, false);
    }

    public CategoryId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public boolean hidden() {
        return hidden;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void show() {
        this.hidden = false;
    }

    public void hide() {
        this.hidden = true;
    }
}
