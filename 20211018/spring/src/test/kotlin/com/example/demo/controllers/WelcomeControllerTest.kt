package com.example.demo.controllers

import com.example.demo.application.MessageGenerator
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.every
import org.hamcrest.CoreMatchers.containsString
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(WelcomeController::class)
internal class WelcomeControllerTest : DescribeSpec() {
    @Autowired
    lateinit var mockMvc: MockMvc

    @MockkBean
    lateinit var messageGenerator: MessageGenerator

    init {
        beforeEach {
            every { messageGenerator.message() } returns "Hello"
        }

        describe("GET /") {
            it("responds with a greeting message") {
                mockMvc.perform(get("/"))
                    .andExpect(status().isOk)
                    .andExpect(content().string(containsString("Hello")))
            }
        }
    }
}