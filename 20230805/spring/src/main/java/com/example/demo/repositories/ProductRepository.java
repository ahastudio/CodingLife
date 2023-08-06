package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.CategoryId;
import com.example.demo.models.Product;
import com.example.demo.models.ProductId;

public interface ProductRepository extends CrudRepository<Product, ProductId> {
    List<Product> findAllByOrderByIdAsc();

    List<Product> findAllByHiddenIsFalseOrderByIdAsc();

    List<Product> findAllByCategoryIdAndHiddenIsFalseOrderByIdAsc(CategoryId id);
}
