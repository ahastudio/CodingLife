package com.ahastudio.board.controllers;

import java.util.List;

import com.ahastudio.board.dtos.PostDto;
import com.ahastudio.board.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<PostDto> list() {
        return postService.posts();
    }

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto create(
            @Validated @RequestBody PostDto postDto
    ) {
//        if (!postDto.isValid()) {
//            throw new InvalidPostDtoException();
//        }
        return postService.create(postDto);
    }

//    @ExceptionHandler(InvalidPostDtoException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public String createFail() {
//        return "멸ㅋ망ㅋ";
//    }
}
