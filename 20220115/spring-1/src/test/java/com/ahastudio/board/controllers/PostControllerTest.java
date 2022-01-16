package com.ahastudio.board.controllers;

import com.ahastudio.board.dtos.PostDto;
import com.ahastudio.board.repositories.PostRepository;
import com.ahastudio.board.services.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private PostService postService;

    @SpyBean
    private PostRepository postRepository;

    @Test
    void list() throws Exception {
        postService.create(new PostDto("Bot", "Post #1", "HI!"));

        mockMvc.perform(MockMvcRequestBuilders.get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("Post #1")
                ));
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{" +
                                "\"author\":\"Tester\"," +
                                "\"title\":\"New Post\"," +
                                "\"body\":\"It's fun!\"" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(
                        containsString("New Post")
                ));
    }
}
