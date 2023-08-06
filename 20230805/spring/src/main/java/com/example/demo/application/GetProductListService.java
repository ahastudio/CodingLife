package com.example.demo.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dtos.ProductListDto;
import com.example.demo.dtos.ProductSummaryDto;
import com.example.demo.dtos.admin.AdminProductListDto;
import com.example.demo.dtos.admin.AdminProductSummaryDto;
import com.example.demo.models.Category;
import com.example.demo.models.CategoryId;
import com.example.demo.models.Product;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;

@Service
public class GetProductListService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public GetProductListService(ProductRepository productRepository,
                                 CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductListDto getProductListDto(String categoryId) {
        List<Product> products = findProducts(categoryId);

        List<ProductSummaryDto> productSummaryDtos = products.stream()
                .map(product -> {
                    Category category = categoryRepository
                            .findById(product.categoryId())
                            .orElseThrow();
                    return ProductSummaryDto.of(product, category);
                })
                .toList();

        return new ProductListDto(productSummaryDtos);
    }

    public AdminProductListDto getAdminProductListDto() {
        List<Product> products = productRepository.findAllByOrderByIdAsc();

        List<AdminProductSummaryDto> productSummaryDtos = products.stream()
                .map(product -> {
                    Category category = categoryRepository
                            .findById(product.categoryId())
                            .orElseThrow();
                    return AdminProductSummaryDto.of(product, category);
                })
                .toList();

        return new AdminProductListDto(productSummaryDtos);
    }

    private List<Product> findProducts(String categoryId) {
        if (categoryId == null || categoryId.isBlank()) {
            return productRepository.findAllByHiddenIsFalseOrderByIdAsc();
        }

        return productRepository.
                findAllByCategoryIdAndHiddenIsFalseOrderByIdAsc(
                        new CategoryId(categoryId));
    }
}
