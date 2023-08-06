package com.example.demo.application;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.demo.models.Category;
import com.example.demo.models.CategoryId;
import com.example.demo.repositories.CategoryRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetCategoryListServiceTest {
    private CategoryRepository categoryRepository;

    private GetCategoryListService getCategoryListService;

    @BeforeEach
    void setUp() {
        categoryRepository = mock(CategoryRepository.class);

        getCategoryListService = new GetCategoryListService(categoryRepository);
    }

    @Test
    @DisplayName("getCategories")
    void list() {
        CategoryId id = new CategoryId("0BV000CAT0001");
        Category category = new Category(id, "top");

        given(categoryRepository.findAllByHiddenFalseOrderByIdAsc())
                .willReturn(List.of(category));

        List<Category> categories = getCategoryListService.getCategories();

        assertThat(categories).hasSize(1);
    }

    @Test
    @DisplayName("getAllCategories")
    void listAll() {
        CategoryId id = new CategoryId("0BV000CAT0001");
        Category category = new Category(id, "top");

        given(categoryRepository.findAllByOrderByIdAsc())
                .willReturn(List.of(category));

        List<Category> categories = getCategoryListService.getAllCategories();

        assertThat(categories).hasSize(1);
    }
}
