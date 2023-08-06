package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Category;
import com.example.demo.models.CategoryId;

public interface CategoryRepository
        extends CrudRepository<Category, CategoryId> {
    List<Category> findAll();
}
