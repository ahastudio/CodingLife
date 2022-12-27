package com.ahastudio.demo.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahastudio.demo.shop.models.Product;
import com.ahastudio.demo.shop.models.ProductId;

public interface ProductRepository extends JpaRepository<Product, ProductId> {
}
