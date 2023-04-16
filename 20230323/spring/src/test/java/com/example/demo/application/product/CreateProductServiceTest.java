package com.example.demo.application.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.models.Money;
import com.example.demo.models.Product;
import com.example.demo.repositories.CommandProductRepository;
import com.example.demo.utils.ImageStorage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateProductServiceTest {
    private ImageStorage imageStorage;

    private CommandProductRepository productRepository;

    private CreateProductService createProductService;

    @BeforeEach
    void setUp() {
        imageStorage = mock(ImageStorage.class);
        productRepository = mock(CommandProductRepository.class);

        createProductService = new CreateProductService(
                imageStorage, productRepository);
    }

    @Test
    void createProduct() {
        String name = "제-품";
        Money price = new Money(100_000L);
        byte[] image = new byte[]{1, 2, 3};

        Product product = createProductService.createProduct(
                name, price, image);

        assertThat(product.name()).isEqualTo(name);
        assertThat(product.price()).isEqualTo(price);

        verify(imageStorage).save(any());
        verify(productRepository).save(product);
    }
}
