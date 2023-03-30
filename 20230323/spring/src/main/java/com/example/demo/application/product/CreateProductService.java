package com.example.demo.application.product;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.models.Money;
import com.example.demo.models.Product;
import com.example.demo.repositories.CommandProductRepository;

@Service
@Transactional
public class CreateProductService {
    private final CommandProductRepository productRepository;

    public CreateProductService(CommandProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(String name, Money price) {
        Product product = Product.create(name, price);

        productRepository.save(product);

        return product;
    }
}
