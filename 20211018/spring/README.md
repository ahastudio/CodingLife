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

## `WelcomeController` 테스트 및 구현 작성

참고:

- [Kotlin + Spring Boot 맛보기](https://github.com/ahastudio/til/blob/main/spring/2019-12-04-kotlin-spring.md)
- [Building web applications with Spring Boot and Kotlin](https://spring.io/guides/tutorials/spring-boot-kotlin/)

`WelcomeControllerTest.kt` 파일 작성:

```kotlin
package com.example.demo.controllers

import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(WelcomeController::class)
internal class WelcomeControllerTest(@Autowired val mockMvc: MockMvc) {

    @Test
    fun `GET ∕`() {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Hello")))
    }

}
```

메서드 이름에 평범한 slash(`/`) 문자을 넣을 수 없어서
[유니코드 slash(`∕`) 문자](https://www.compart.com/en/unicode/U+2215)를
사용함.

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
