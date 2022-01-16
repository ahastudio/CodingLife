package com.ahastudio.board.controllers;

import java.util.List;

import com.ahastudio.board.models.Post;
import com.ahastudio.board.repositories.PostRepository;
import com.ahastudio.board.services.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private PostService postService;

    @MockBean
    private PostRepository postRepository;

    @BeforeEach
    void setupPostRepository() {
        given(postRepository.findAll())
                .willReturn(List.of(new Post(1L, "Bot", "Post #1", "HI!")));

        given(postRepository.getById(1L))
                .willReturn(new Post(1L, "Bot", "Post #1", "HI!"));

        given(postRepository.save(any(Post.class)))
                .willReturn(new Post(2L, "Tester", "New Post", "It's fun!"));
    }

    @Test
    void list() throws Exception {
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

        verify(postRepository).save(any(Post.class));
    }

    @Test
    void createWithBlankAuthor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{" +
                                "\"author\":\"\"," +
                                "\"title\":\"New Post\"," +
                                "\"body\":\"It's fun!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createWithBlankTitle() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{" +
                                "\"author\":\"Tester\"," +
                                "\"title\":\"\"," +
                                "\"body\":\"It's fun!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createWithBlankBody() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{" +
                                "\"author\":\"Tester\"," +
                                "\"title\":\"New Post\"," +
                                "\"body\":\"\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/posts/1"))
                .andExpect(status().isOk());
    }
}
