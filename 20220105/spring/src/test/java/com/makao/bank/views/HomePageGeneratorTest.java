package com.makao.bank.views;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HomePageGeneratorTest {
    @Test
    void content() {
        PageGenerator pageGenerator = new HomePageGenerator();

        String html = pageGenerator.content();

        assertThat(html).contains("Hello, world!");
    }

    @Test
    void html() {
        PageGenerator pageGenerator = new HomePageGenerator();

        String html = pageGenerator.html();

        assertThat(html).contains("Hello, world!");
        assertThat(html).contains("<html");
    }
}
