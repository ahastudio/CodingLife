package com.example.demo.controllers

import com.example.demo.application.MessageGenerator
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WelcomeController(val messageGenerator: MessageGenerator) {
    @GetMapping
    fun home(): String {
        return messageGenerator.message()
    }
}
