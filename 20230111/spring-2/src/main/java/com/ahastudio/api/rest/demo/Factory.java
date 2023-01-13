package com.ahastudio.api.rest.demo;

import com.ahastudio.api.rest.demo.repositories.PostRepository;

public class Factory {
    private static PostRepository postRepository;

    public static PostRepository postRepository() {
        if (postRepository == null) {
            postRepository = new PostRepository();
        }
        return postRepository;
    }
}
