package com.example.demo.dtos;

import com.example.demo.models.Image;

public record ImageDto(
        String url
) {
    public static ImageDto of(Image image) {
        return new ImageDto(image.url());
    }
}
