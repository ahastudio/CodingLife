package com.ahastudio.board.repositories;

import java.util.ArrayList;
import java.util.List;

import com.ahastudio.board.models.Post;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepository {
    private final List<Post> posts = new ArrayList<>();

    private Long id = 1L;

    public List<Post> findAll() {
        return new ArrayList<>(posts);
    }

    public Post save(Post post) {
        Post savedPost = new Post(id, post);

        // TODO: Multi-thread 상황(바로 지금!)에선 문제 발생 가능.
        //       -> Spring Data JPA로 해결하자!
        id += 1;

        posts.add(savedPost);

        return savedPost;
    }
}
