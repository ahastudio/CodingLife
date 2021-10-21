package com.example.demo.application

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class MessageGeneratorTest : DescribeSpec({
    val messageGenerator = MessageGenerator()

    describe("message") {
        it("returns a greeting message") {
            messageGenerator.message() shouldBe "Hello, world!"
        }
    }
})
