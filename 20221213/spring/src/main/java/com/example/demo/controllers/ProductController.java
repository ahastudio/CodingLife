package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.modelmapper.ModelMapper;

import com.example.demo.controllers.dtos.ProductDetailDto;
import com.example.demo.controllers.dtos.ProductListDto;
import com.example.demo.exceptions.ProductNotFound;
import com.example.demo.models.Product;
import com.example.demo.models.ProductId;
import com.example.demo.repositories.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductController(ProductRepository productRepository,
                             ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ProductListDto list() {
        List<Product> products = productRepository.findAll();

        return ProductListDto.builder()
                .products(modelsToDtos(products))
                .build();
    }

    @GetMapping("/{id}")
    public ProductDetailDto detail(@PathVariable Long id) {
        Product product = productRepository.findById(new ProductId(id))
                .orElseThrow(ProductNotFound::new);

        return mapToDto(product);
    }

    @ExceptionHandler(ProductNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String productNotFound() {
        return "Product Not Found";
    }

    private List<ProductListDto.Product> modelsToDtos(List<Product> products) {
        return products.stream()
                .map(product ->
                        modelMapper.map(product, ProductListDto.Product.class))
                .toList();
    }

    private ProductDetailDto mapToDto(Product product) {
        return modelMapper.map(product, ProductDetailDto.class);
    }
}
