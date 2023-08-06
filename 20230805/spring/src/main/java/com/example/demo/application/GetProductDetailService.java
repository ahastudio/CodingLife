package com.example.demo.application;

import org.springframework.stereotype.Service;

import com.example.demo.dtos.ProductDetailDto;
import com.example.demo.dtos.admin.AdminProductDetailDto;
import com.example.demo.models.Category;
import com.example.demo.models.Product;
import com.example.demo.models.ProductId;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;

@Service
public class GetProductDetailService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public GetProductDetailService(ProductRepository productRepository,
                                   CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductDetailDto getProductDetailDto(String productId) {
        ProductId id = new ProductId(productId);
        Product product = productRepository.findById(id)
                .orElseThrow();

        Category category = categoryRepository
                .findById(product.categoryId())
                .orElseThrow();

        return ProductDetailDto.of(product, category);
    }

    public AdminProductDetailDto getAdminProductDetailDto(ProductId productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow();

        Category category = categoryRepository
                .findById(product.categoryId())
                .orElseThrow();

        return AdminProductDetailDto.of(product, category);
    }
}
