package com.ahastudio.api.rest.demo.models;

public class Post {
    private PostId id;

    private String title;

    private MultilineText content;

    public Post(PostId id, String title, MultilineText content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Post(String title, MultilineText content) {
        this.id = PostId.generate();
        this.title = title;
        this.content = content;
    }

    public void update(String title, MultilineText content) {
        // 내가 고칠 수 있는지 권한 검사
        // title에 대한 유효성 검사
        // 기타 등등 치명적인 무언가... 확인하기!

        this.title = title;
        this.content = content;
    }

    // Getter는 절대로 비즈니스 로직을 위해 쓰지 않는다.

    public PostId id() {
        return id;
    }

    public String title() {
        return title;
    }

    public MultilineText content() {
        return content;
    }
}
