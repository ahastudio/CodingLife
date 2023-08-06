package com.example.demo.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Category;
import com.example.demo.models.CategoryId;
import com.example.demo.repositories.CategoryRepository;

@Service
@Transactional(readOnly = true)
public class GetCategoryDetailService {
    private final CategoryRepository categoryRepository;

    public GetCategoryDetailService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category getCategory(CategoryId categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow();
    }
}
