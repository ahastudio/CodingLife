package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controllers.dtos.GreetingDto;

@RestController
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public GreetingDto home() {
        return new GreetingDto("안녕!");
    }
}
