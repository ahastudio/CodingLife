package com.example.demo.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Category;
import com.example.demo.repositories.CategoryRepository;

@Service
@Transactional(readOnly = true)
public class GetCategoryListService {
    private final CategoryRepository categoryRepository;

    public GetCategoryListService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAllByHiddenFalseOrderByIdAsc();
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAllByOrderByIdAsc();
    }
}
