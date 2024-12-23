package com.example.demo.controllers;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dtos.UploadImageDto;
import com.example.demo.utils.ImageStorage;

@RestController
@RequestMapping("images")
public class ImageController {
    private final ImageStorage imageStorage;

    public ImageController(ImageStorage imageStorage) {
        this.imageStorage = imageStorage;
    }

    @CrossOrigin
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String create(
            @ModelAttribute UploadImageDto imageDto
    ) throws IOException {
        MultipartFile multipartFile = imageDto.image();

        if (multipartFile == null || multipartFile.isEmpty()) {
            return "No image";
        }

        return imageStorage.save(multipartFile.getBytes());
    }
}
