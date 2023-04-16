package com.example.demo.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.hypersistence.tsid.TSID;

import com.example.demo.application.product.CreateProductService;
import com.example.demo.application.product.GetProductListService;
import com.example.demo.controllers.dtos.CreateProductDto;
import com.example.demo.controllers.dtos.ProductListDto;
import com.example.demo.models.Money;

@RestController
@RequestMapping("products")
public class ProductController {
    private final GetProductListService getProductListService;
    private final CreateProductService createProductService;

    public ProductController(GetProductListService getProductListService,
                             CreateProductService createProductService) {
        this.getProductListService = getProductListService;
        this.createProductService = createProductService;
    }

    @GetMapping
    public ProductListDto list() {
        return getProductListService.getProductListDto();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Secured("ROLE_ADMIN")
    public String create(
            @Valid @ModelAttribute CreateProductDto productDto
    ) throws IOException {
        String name = productDto.name().strip();
        Money price = new Money(productDto.price());
        MultipartFile multipartFile = productDto.image();

        // Just Test! :)
        uploadFile(multipartFile);

        byte[] image = readMultipartFile(multipartFile);
        createProductService.createProduct(name, price, image);

        return "Created!";
    }

    private void uploadFile(MultipartFile multipartFile) throws IOException {
        String id = TSID.Factory.getTsid().toString();
        File file = new File("data/" + id + ".jpg");

        try (
                InputStream inputStream = multipartFile.getInputStream();
                OutputStream outputStream = new FileOutputStream(file)
        ) {
            byte[] content = inputStream.readAllBytes();
            outputStream.write(content);
        }
    }

    private byte[] readMultipartFile(MultipartFile multipartFile)
            throws IOException {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return null;
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            return inputStream.readAllBytes();
        }
    }
}
