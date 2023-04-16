package com.example.demo.application.product;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.models.Money;
import com.example.demo.models.Product;
import com.example.demo.repositories.CommandProductRepository;
import com.example.demo.utils.ImageStorage;

@Service
@Transactional
public class CreateProductService {
    private final ImageStorage imageStorage;
    private final CommandProductRepository productRepository;

    public CreateProductService(ImageStorage imageStorage,
                                CommandProductRepository productRepository) {
        this.imageStorage = imageStorage;
        this.productRepository = productRepository;
    }

    public Product createProduct(String name, Money price, byte[] image) {
        Product product = Product.create(name, price);

        if (image != null) {
            String imageUrl = imageStorage.save(image);
            product.changeImage(imageUrl);
        }

        productRepository.save(product);

        return product;
    }
}
