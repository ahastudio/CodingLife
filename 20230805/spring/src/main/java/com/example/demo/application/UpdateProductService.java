package com.example.demo.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dtos.admin.AdminUpdateProductDto;
import com.example.demo.models.Product;
import com.example.demo.models.ProductId;
import com.example.demo.repositories.ProductRepository;

@Service
@Transactional
public class UpdateProductService {
    private final ProductRepository productRepository;

    public UpdateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void updateProduct(
            ProductId productId, AdminUpdateProductDto productDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow();

        product.update(productDto);
    }
}
