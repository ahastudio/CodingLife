package com.example.demo.application

import org.springframework.stereotype.Service

@Service
class MessageGenerator {
    fun message(): String {
        return "Hello, world!"
    }
}
