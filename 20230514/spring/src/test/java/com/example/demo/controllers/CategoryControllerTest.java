package com.example.demo.controllers;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.application.GetCategoryListService;
import com.example.demo.models.Category;
import com.example.demo.models.CategoryId;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest extends ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetCategoryListService getCategoryListService;

    @Test
    @DisplayName("GET /categories")
    void list() throws Exception {
        CategoryId id = new CategoryId("0BV000CAT0001");
        Category category = new Category(id, "top");

        given(getCategoryListService.getCategories())
                .willReturn(List.of(category));

        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("top")));
    }
}
