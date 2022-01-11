package com.example.demo.controllers;

import java.util.List;

import com.example.demo.dtos.PostDto;
import com.example.demo.models.Post;
import com.example.demo.services.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@WebMvcTest(PostController.class)
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    void list() throws Exception {
        Post mockPost = new Post(1L, "me", "Post #1", "Once upon a time");

        given(postService.list()).willReturn(List.of(mockPost));

        mockMvc.perform(MockMvcRequestBuilders.get("/posts"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(
                        containsString("\"title\":\"Post #1\"")
                ));
    }

    @Test
    void detail() throws Exception {
        Post mockPost = new Post(1L, "me", "Post #1", "Once upon a time");

        given(postService.get(1L)).willReturn(mockPost);

        mockMvc.perform(MockMvcRequestBuilders.get("/posts/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(
                        containsString("\"title\":\"Post #1\"")
                ));
    }

    @Test
    void create() throws Exception {
        given(postService.create(any(PostDto.class))).willReturn(
                new Post(2L, "Test", "Subject", "Content")
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"author\":\"Tester\"," +
                                "\"title\":\"Subject\"," +
                                "\"body\":\"Content\"" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(
                        containsString("\"title\":\"Subject\"")
                ));

        verify(postService, only()).create(any(PostDto.class));
    }

    @Test
    void createWithoutTitle() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"author\":\"Tester\"," +
                                "\"title\":\"\"," +
                                "\"body\":\"Content\"" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(postService, never()).create(any(PostDto.class));
    }
}
