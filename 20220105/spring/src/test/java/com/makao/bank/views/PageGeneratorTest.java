package com.makao.bank.views;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PageGeneratorTest {
    @Test
    void html() {
        PageGenerator pageGenerator = new PageGenerator() {
            @Override
            public String content() {
                return "Hello!";
            }
        };

        String html = pageGenerator.html();

        assertThat(html).contains("Hello!");
        assertThat(html).contains("<html");
    }

    @Test
    void navigation() {
        PageGenerator pageGenerator = new PageGenerator() {
            @Override
            public String content() {
                return "Hello!";
            }
        };

        String html = pageGenerator.html();

        assertThat(html).contains("<a href=\"/\">Home");
        assertThat(html).contains("<a href=\"/account\">잔액 조회");
        assertThat(html).contains("<a href=\"/transfer\">계좌 이체");
    }
}
