package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Product;
import com.example.demo.models.ProductId;

public interface ProductRepository extends JpaRepository<Product, ProductId> {
}
