package com.example.demo.repositories;

import java.util.List;

import com.example.demo.models.Product;

public interface SearchProductRepository {
    List<Product> findAllByName(String name);
}
