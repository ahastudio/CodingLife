package com.ahastudio.api.rest.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ahastudio.api.rest.demo.exceptions.PostNotFound;

@RestController
@RequestMapping("/posts")
public class PostController {
    @GetMapping
    public String list() {
        return "게시물 목록\n";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable String id) {
        if (id.equals("404")) {
            throw new PostNotFound();
        }

        return "게시물 상세: " + id + "\n";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody String body) {
        return "{\"action\": \"게시물 생성\", \"body\": \"" +
                body.replace("\"", "\\\"") + "\"}";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable String id, @RequestBody String body) {
        return "{\"action\": \"게시물 수정: " + id + "\", \"body\": \"" +
                body.replace("\"", "\\\"") + "\"}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        return "게시물 삭제: " + id;
    }

    @ExceptionHandler(PostNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String postNotFound() {
        return "게시물을 찾을 수 없습니다\n";
    }
}
