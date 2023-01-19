package com.ahastudio.api.rest.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.ahastudio.api.rest.demo.dtos.PostDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PostFeatureTest {
    @Value("${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("게시물을 추가하고 목록에서 확인되는지 테스트")
    void list() {
        String url = "http://localhost:" + port + "/posts";

        restTemplate.postForLocation(url, new PostDto("ID", "NEW POST", "."));
        restTemplate.postForLocation(url, new PostDto("ID", "새 글", "제곧내"));

        String body = restTemplate.getForObject(url, String.class);

        assertThat(body).contains("NEW POST");
        assertThat(body).contains("새 글");
        assertThat(body).contains("제곧내");

        String id = findLastId(body);

        restTemplate.delete(url + "/" + id);

        body = restTemplate.getForObject(url, String.class);

        assertThat(body).contains("NEW POST");

        assertThat(body).doesNotContain(id);
        assertThat(body).doesNotContain("새 글");
        assertThat(body).doesNotContain("제곧내");
    }

    private String findLastId(String body) {
        Pattern pattern = Pattern.compile("\"id\":\"([^\"]+)\"");
        Matcher matcher = pattern.matcher(body);

        String id = "";
        while (matcher.find()) {
            id = matcher.group(1);
        }
        return id;
    }
}
