package com.ahastudio.board.repositories;

import java.util.List;

import com.ahastudio.board.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

@SuppressWarnings("unchecked")
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAll();

    Post save(Post post);
}
