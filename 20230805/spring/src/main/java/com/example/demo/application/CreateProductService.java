package com.example.demo.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.CategoryId;
import com.example.demo.models.Image;
import com.example.demo.models.Money;
import com.example.demo.models.Product;
import com.example.demo.models.ProductId;
import com.example.demo.models.ProductOption;
import com.example.demo.repositories.ProductRepository;

@Service
@Transactional
public class CreateProductService {
    private final ProductRepository productRepository;

    public CreateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(
            CategoryId categoryId, List<Image> images,
            String name, Money price, List<ProductOption> options,
            String description) {
        ProductId id = ProductId.generate();

        Product product = new Product(
                id, categoryId, images, name, price, options, description);

        productRepository.save(product);

        return product;
    }
}
