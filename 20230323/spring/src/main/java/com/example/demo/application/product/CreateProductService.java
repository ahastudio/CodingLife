package com.example.demo.application.product;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.models.Money;
import com.example.demo.models.Product;
import com.example.demo.repositories.CommandProductRepository;
import com.example.demo.repositories.ProductRepository;

@Service
@Transactional
public class CreateProductService {
    private final CommandProductRepository productRepository;

    public CreateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(String name, Money price) {
        Product product = Product.create(name, price);

        productRepository.save(product);

        return product;
    }
}
