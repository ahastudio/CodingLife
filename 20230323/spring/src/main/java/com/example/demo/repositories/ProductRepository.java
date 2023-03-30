package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Product;
import com.example.demo.models.ProductId;

public interface ProductRepository extends
        SearchProductRepository, CommandProductRepository,
        CrudRepository<Product, ProductId> {
}
