package com.example.demo.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Category;
import com.example.demo.models.CategoryId;
import com.example.demo.repositories.CategoryRepository;

@Service
@Transactional
public class CreateCategoryService {
    private final CategoryRepository categoryRepository;

    public CreateCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void createCategory(String name) {
        Category category = new Category(CategoryId.generate(), name, true);

        categoryRepository.save(category);
    }
}
