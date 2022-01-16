package com.ahastudio.board.repositories;

import com.ahastudio.board.models.Post;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PostRepositoryTest {
    @Test
    void findAll() {
        PostRepository postRepository = new PostRepository();

        assertThat(postRepository.findAll()).hasSize(0);

        Post post = new Post(null, "Author", "Title", "Body");
        postRepository.save(post);

        assertThat(postRepository.findAll()).hasSize(1);
    }

    @Test
    void save() {
        PostRepository postRepository = new PostRepository();

        Post post1 = postRepository.save(
                new Post(null, "Author", "Title", "Body"));
        Post post2 = postRepository.save(
                new Post(null, "Author", "Title", "Body"));

        assertThat(post1.id()).isNotNull();
        assertThat(post2.id()).isNotNull();

        assertThat(post1.id()).isNotEqualTo(post2.id());
    }
}