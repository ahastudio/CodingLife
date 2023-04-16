package com.example.demo.controllers.dtos;

import org.springframework.web.multipart.MultipartFile;

public record UploadImageDto(
        MultipartFile image
) {
}
