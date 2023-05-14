package com.example.demo.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dtos.ProductListDto;
import com.example.demo.dtos.ProductSummaryDto;
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
                            .get();
                    return ProductSummaryDto.of(product, category);
                })
                .toList();

        return new ProductListDto(productSummaryDtos);
    }

    private List<Product> findProducts(String categoryId) {
        if (categoryId == null) {
            return productRepository.findAll();
        }

        CategoryId id = new CategoryId(categoryId);
        return productRepository.findAllByCategoryId(id);
    }
}
