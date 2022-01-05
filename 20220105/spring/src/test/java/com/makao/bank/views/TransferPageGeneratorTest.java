package com.makao.bank.views;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TransferPageGeneratorTest {
    @Test
    void content() {
        PageGenerator pageGenerator = new TransferPageGenerator();

        String html = pageGenerator.content();

        assertThat(html).contains("받는 사람 계좌:");
        assertThat(html).contains("<input type=\"text\" name=\"to\"");
        assertThat(html).contains("보낼 금액:");
        assertThat(html).contains("<input type=\"number\" name=\"amount\"");
        assertThat(html).contains("확인");
    }
}
