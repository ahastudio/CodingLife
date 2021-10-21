# Kotlin + Spring demo

## 실행

Run tests:

```bash
./gradlew test
```

Build JAR:

```bash
./gradlew bootJar
```

Run web server:

```bash
./gradlew bootRun
```

<http://localhost:8080/>

## 기본 코드 생성

Spring Initializr 사용:
<https://start.spring.io/>

## Kotest 설정

- [Quick Start | Kotest](https://kotest.io/docs/quickstart)
- [Spring | Kotest](https://kotest.io/docs/extensions/spring.html)
- [Project Level Config | Kotest](https://kotest.io/docs/framework/project-config.html)

`build.gradle.kt` 파일에
[Kotest](https://kotest.io/)와
[MockK](https://mockk.io/),
[SpringMockK](https://github.com/Ninja-Squad/springmockk)
의존성 추가:

```koltlin
dependencies {
    // ...(전략)...

    testImplementation("io.kotest:kotest-runner-junit5:5.0.0.M3")
    testImplementation("io.kotest:kotest-extensions-spring:4.4.3")

    testImplementation("io.mockk:mockk:1.12.0")
    testImplementation("com.ninja-squad:springmockk:3.0.1")
}
```

```kotlin
package com.example.demo

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.extensions.spring.SpringExtension

object ProjectConfig : AbstractProjectConfig() {
    override fun extensions() = listOf(SpringExtension)
}
```

✅ IntelliJ IDEA에
[Kotest 플러그인](https://plugins.jetbrains.com/plugin/14080-kotest)
설치.

## `WelcomeController` 테스트 및 구현 작성

참고:

- [Kotlin + Spring Boot 맛보기](https://github.com/ahastudio/til/blob/main/spring/2019-12-04-kotlin-spring.md)
- [Building web applications with Spring Boot and Kotlin](https://spring.io/guides/tutorials/spring-boot-kotlin/)

`WelcomeControllerTest.kt` 파일 작성:

```kotlin
package com.example.demo.controllers

import io.kotest.core.spec.style.DescribeSpec
import org.hamcrest.CoreMatchers.containsString
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(WelcomeController::class)
internal class WelcomeControllerTest(
    @Autowired val mockMvc: MockMvc
) : DescribeSpec({
    describe("GET /") {
        it("responds with a greeting message") {
            mockMvc.perform(get("/"))
                .andExpect(status().isOk)
                .andExpect(content().string(containsString("Hello")))
        }
    }
})
```

`WelcomeController.kt` 파일 작성:

```kotlin
package com.example.demo.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WelcomeController {
    @GetMapping
    fun home(): String {
        return "Hello, world!"
    }
}
```

## `MessageGenerator` 분리

`MessageGeneratorTest.kt` 파일에 테스트 작성:

```kotlin
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
```

`MessageGenerator.kt` 파일에 구현:

```kotlin
package com.example.demo.application

import org.springframework.stereotype.Service

@Service
class MessageGenerator {
    fun message(): String {
        return "Hello, world!"
    }
}
```

`WelcomeController.kt` 파일 수정:

```kotlin
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
```

`WelcomeControllerTest.kt` 파일 수정:

```kotlin
package com.example.demo.controllers

import com.example.demo.application.MessageGenerator
import com.ninjasquad.springmockk.SpykBean
import io.kotest.core.spec.style.DescribeSpec
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

    @SpykBean
    lateinit var messageGenerator: MessageGenerator

    init {
        describe("GET /") {
            it("responds with a greeting message") {
                mockMvc.perform(get("/"))
                    .andExpect(status().isOk)
                    .andExpect(content().string(containsString("Hello")))
            }
        }
    }
}
```

## Controller 테스트에서 `MessageGenerator`를 Mock으로 처리

`WelcomeControllerTest.kt` 파일 수정:

```kotlin
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

    // SpykBean → MockkBean
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
```
