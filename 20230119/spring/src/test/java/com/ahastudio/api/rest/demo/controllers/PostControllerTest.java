package com.ahastudio.api.rest.demo.controllers;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ahastudio.api.rest.demo.application.CreatePostService;
import com.ahastudio.api.rest.demo.application.DeletePostService;
import com.ahastudio.api.rest.demo.application.GetPostService;
import com.ahastudio.api.rest.demo.application.GetPostsService;
import com.ahastudio.api.rest.demo.application.UpdatePostService;
import com.ahastudio.api.rest.demo.dtos.PostDto;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetPostsService getPostsService;

    @MockBean
    private GetPostService getPostService;

    @MockBean
    private CreatePostService createPostService;

    @MockBean
    private UpdatePostService updatePostService;

    @MockBean
    private DeletePostService deletePostService;

    @Test
    void list() throws Exception {
        given(getPostsService.getPostDtos()).willReturn(List.of(
                new PostDto("001", "제목", "테스트입니다"),
                new PostDto("002", "second", "post")
        ));

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("제목")
                ));
    }

    @Test
    void create() throws Exception {
        String json = """
                {
                  "title": "새 글",
                  "content": "제곧내"
                }
                """;

        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isCreated());

        verify(createPostService).createPost(any(PostDto.class));

//        verify(postRepository).save(argThat(post -> {
//            return getFieldValue(post, "title").equals("새 글") &&
//                    getFieldValue(post, "content")
//                            .equals(new MultilineText("제곧내"));
//        }));
    }

    @Test
    void deletePost() throws Exception {
        String id = "001";

        mockMvc.perform(delete("/posts/" + id))
                .andExpect(status().isOk());

        verify(deletePostService).deletePost(id);
    }

    private Object getFieldValue(Object object, String fieldName) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
