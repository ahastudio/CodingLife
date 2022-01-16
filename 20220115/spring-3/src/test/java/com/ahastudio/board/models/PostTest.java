package com.ahastudio.board.models;

import com.ahastudio.board.dtos.PostDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PostTest {
    @Test
    void creation() {
        Post post = new Post(1L, "Tester", "Fun story", "Joke");

        assertThat(post.id()).isEqualTo(1L);
    }

    @Test
    void copy() {
        Post post1 = new Post(1L, "Tester", "Fun story", "Joke");
        Post post2 = new Post(2L, post1);

        assertThat(post1.id()).isEqualTo(1L);
        assertThat(post2.id()).isEqualTo(2L);

        assertThat(post1.toDto()).isEqualTo(new Post(1L, post2).toDto());
    }

    @Test
    void convertToDTO() {
        Post post = new Post(1L, "Tester", "Fun story", "Joke");

        PostDto postDto = post.toDto();

        assertThat(postDto)
                .isEqualTo(new PostDto(1L, "Tester", "Fun story", "Joke"));
    }

    @Test
    void createFromDTO() {
        PostDto postDto = new PostDto(1L, "Tester", "Fun story", "Joke");

        Post post = Post.of(postDto);

        assertThat(postDto)
                .isEqualTo(new PostDto(1L, "Tester", "Fun story", "Joke"));
    }
}
