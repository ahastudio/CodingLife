# Post API

- <https://docs.spring.io/spring-boot/docs/current/reference/html/web.html>
- <https://docs.spring.io/spring-boot/docs/current/reference/html/data.html>
- <https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html>

## 프로젝트 생성

Spring Initialzr에서 의존성 추가:

1. Spring Web
2. Spring Boot DevTools
3. Validation
4. Spring Data JPA
5. H2 Database

## 기본 설정 및 실행

`src/main/resources/application.propertices` 파일 수정.

```properties
server.port=3000

spring.datasource.url=jdbc:h2:~/data/post-api-demo
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=create-drop

spring.h2.console.enabled=true
```

Run web server:

```bash
./gradlew bootRun
```

<http://localhost:3000/>

Run all tests:

```bash
./gradlew test
```

## HomeController 추가

`src/test/java/.../controllers/WelcomeControllerTest.java` 파일 작성.

```java
package com.example.demo.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;

@WebMvcTest(WelcomeController.class)
class WelcomeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void home() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(
                        containsString("Hello, world")
                ));
    }
}
```

`src/main/java/.../controllers/WelcomeController.java` 파일 작성.

```java
package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping("/")
    String home() {
        return "Hello, world!";
    }
}
```
