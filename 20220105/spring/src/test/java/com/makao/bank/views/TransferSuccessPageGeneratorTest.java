package com.makao.bank.views;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TransferSuccessPageGeneratorTest {
    @Test
    void content() {
        PageGenerator pageGenerator = new TransferSuccessPageGenerator();

        String html = pageGenerator.content();

        assertThat(html).contains("계좌 이체 성공!");
    }
}
