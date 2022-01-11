package com.example.demo.services;

import java.util.List;

import com.example.demo.dtos.PostDto;
import com.example.demo.models.Post;
import com.example.demo.repositories.PostRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class PostServiceTest {
    @Test
    void list() {
        Post mockPost = new Post(1L, "Author", "Title", "Body");

        PostRepository postRepository = mock(PostRepository.class);
        given(postRepository.findAll()).willReturn(List.of(mockPost));

        PostService postService = new PostService(postRepository);

        List<Post> posts = postService.list();

        assertThat(posts).hasSize(1);
    }

    @Test
    void get() {
        Long id = 1L;

        Post mockPost = new Post(id, "Author", "Title", "Body");

        PostRepository postRepository = mock(PostRepository.class);
        given(postRepository.getById(id)).willReturn(mockPost);

        PostService postService = new PostService(postRepository);

        Post post = postService.get(id);

        assertThat(post.id()).isEqualTo(id);
    }

    @Test
    void create() {
        Long id = 1004L;

        PostDto postDto = new PostDto(null, "Author", "Title", "Body");
        Post mockPost = new Post(id, "Author", "Title", "Body");

        PostRepository postRepository = mock(PostRepository.class);
        given(postRepository.save(any(Post.class))).willReturn(mockPost);

        PostService postService = new PostService(postRepository);

        Post post = postService.create(postDto);

        assertThat(post.id()).isEqualTo(id);
    }
}
