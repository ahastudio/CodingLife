package com.makao.bank.controllers;

import com.makao.bank.views.HomePageGenerator;
import com.makao.bank.views.PageGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        PageGenerator pageGenerator = new HomePageGenerator();
        return pageGenerator.html();
    }
}
