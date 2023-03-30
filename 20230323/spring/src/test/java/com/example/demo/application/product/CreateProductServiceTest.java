package com.example.demo.application.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.models.Money;
import com.example.demo.models.Product;
import com.example.demo.repositories.CommandProductRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateProductServiceTest {
    private CommandProductRepository productRepository;

    private CreateProductService createProductService;

    @BeforeEach
    void setUp() {
        productRepository = mock(CommandProductRepository.class);

        createProductService = new CreateProductService(productRepository);
    }

    @Test
    void createProduct() {
        String name = "제-품";
        Money price = new Money(100_000L);

        Product product = createProductService.createProduct(name, price);

        assertThat(product.name()).isEqualTo(name);
        assertThat(product.price()).isEqualTo(price);

        verify(productRepository).save(product);
    }
}
