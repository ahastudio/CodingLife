package com.example.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.GetCategoryListService;
import com.example.demo.dtos.CategoryDto;
import com.example.demo.dtos.CategoryListDto;
import com.example.demo.models.Category;

@CrossOrigin
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final GetCategoryListService getCategoryListService;

    public CategoryController(GetCategoryListService getCategoryListService) {
        this.getCategoryListService = getCategoryListService;
    }

    @GetMapping
    public CategoryListDto list() {
        List<Category> categories = getCategoryListService.getCategories();

        List<CategoryDto> categoryDtos = categories.stream()
                .map(category -> mapToDto(category))
                .toList();

        return new CategoryListDto(categoryDtos);
    }

    private CategoryDto mapToDto(Category category) {
        return new CategoryDto(category.id().toString(), category.name());
    }
}
