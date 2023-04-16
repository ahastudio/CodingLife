package com.example.demo.dtos;

import org.springframework.web.multipart.MultipartFile;

public record UploadImageDto(
        MultipartFile image
) {
}
