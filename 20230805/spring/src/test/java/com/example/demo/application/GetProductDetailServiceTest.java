package com.example.demo.application;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.Fixtures;
import com.example.demo.dtos.admin.AdminProductDetailDto;
import com.example.demo.models.Category;
import com.example.demo.models.CategoryId;
import com.example.demo.models.Product;
import com.example.demo.models.ProductId;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetProductDetailServiceTest {
    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    private GetProductDetailService getProductDeailService;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        categoryRepository = mock(CategoryRepository.class);

        getProductDeailService = new GetProductDetailService(
                productRepository, categoryRepository);
    }

    @Test
    void getAdminProductDetailDto() {
        Product product = Fixtures.product("맨투맨");
        ProductId productId = product.id();

        CategoryId categoryId = product.categoryId();
        Category category = new Category(categoryId, "카테고리", false);

        given(productRepository.findById(productId))
                .willReturn(Optional.of(product));

        given(categoryRepository.findById(categoryId))
                .willReturn(Optional.of(category));

        AdminProductDetailDto productDto =
                getProductDeailService.getAdminProductDetailDto(productId);

        assertThat(productDto.name()).isEqualTo("맨투맨");
    }
}
