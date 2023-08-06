package com.example.demo.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "images")
public class Image {
    @EmbeddedId
    private ImageId id;

    @Column(name = "url")
    private String url;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private Image() {
    }

    public Image(ImageId id, String url) {
        this.id = id;
        this.url = url;
    }

    public ImageId id() {
        return id;
    }

    public String url() {
        return url;
    }

    public void changeUrl(String url) {
        this.url = url;
    }
}
