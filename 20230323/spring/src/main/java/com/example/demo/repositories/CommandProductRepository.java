package com.example.demo.repositories;

import com.example.demo.models.Product;

public interface CommandProductRepository {
    Product save(Product product);
}
