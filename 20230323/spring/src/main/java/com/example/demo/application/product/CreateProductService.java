package com.example.demo.application.product;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.models.Money;
import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;

@Service
@Transactional
public class CreateProductService {
    private final ProductRepository productRepository;

    public CreateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct() {
        Product product = Product.create("제-품", new Money(100_000L));

        productRepository.save(product);

        return product;
    }
}
