package com.ahastudio.demo.shop.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ahastudio.demo.shop.controllers.dtos.ProductDetailDto;
import com.ahastudio.demo.shop.controllers.dtos.ProductListDto;
import com.ahastudio.demo.shop.exceptions.ProductNotFound;
import com.ahastudio.demo.shop.models.Product;
import com.ahastudio.demo.shop.models.ProductId;
import com.ahastudio.demo.shop.repositories.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public ProductListDto list() {
        List<Product> products = productRepository.findAll();

        return new ProductListDto(
                products.stream()
                        .map(product -> new ProductListDto.Product(
                                product.id().toString(),
                                new ProductListDto.Category("", ""),
                                new ProductListDto.Image(""),
                                product.name(),
                                product.price().asLong()
                        ))
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ProductDetailDto detail(@PathVariable String id) {
        Product product = productRepository.findById(new ProductId(id))
                .orElseThrow(ProductNotFound::new);

        return new ProductDetailDto(
                product.id().toString(),
                product.name(),
                product.price().asLong()
        );
    }

    @ExceptionHandler(ProductNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound() {
        return "Product Not Found";
    }
}
