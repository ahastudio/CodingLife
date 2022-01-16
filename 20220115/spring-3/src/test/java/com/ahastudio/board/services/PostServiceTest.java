package com.ahastudio.board.services;

import java.util.List;

import com.ahastudio.board.dtos.PostDto;
import com.ahastudio.board.models.Post;
import com.ahastudio.board.repositories.PostRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class PostServiceTest {
    @Test
    void posts() {
        PostRepository postRepository = mock(PostRepository.class);

        given(postRepository.findAll()).willReturn(List.of(
                new Post(1L, "Tester", "Post #1", "Hello"),
                new Post(2L, "Admin", "Post #2", "Hello")
        ));

        PostService postService = new PostService(postRepository);

        assertThat(postService.posts()).hasSize(2);
    }

    @Test
    void create() {
        PostRepository postRepository = mock(PostRepository.class);
        given(postRepository.save(any()))
                .willReturn(new Post(1L, "Tester", "New Post", "Hello"));

        PostService postService = new PostService(postRepository);

        PostDto postDto = postService.create(
                new PostDto("Tester", "New Post", "Hello"));

        assertThat(postDto.getId()).isNotNull();
        assertThat(postDto.getAuthor()).isEqualTo("Tester");
    }

    @Test
    void update() {
        Post post = spy(new Post(1L, "Author", "Title", "Body"));

        PostRepository postRepository = mock(PostRepository.class);
        given(postRepository.getById(1L)).willReturn(post);

        PostService postService = new PostService(postRepository);

        PostDto postDto = postService.update();

        assertThat(postDto).isNotNull();

        verify(postRepository).getById(any(Long.class));
        verify(post).decorateTitle();
    }
}
