package com.makao.bank.views;

public class TransferPageGenerator extends PageGenerator {
    @Override
    public String content() {
        return "<form method=\"POST\">\n" +
                "<p>\n" +
                "받는 사람 계좌:\n" +
                "<input type=\"text\" name=\"to\">\n" +
                "</p>\n" +
                "<p>\n" +
                "보낼 금액:\n" +
                "<input type=\"number\" name=\"amount\">\n" +
                "</p>\n" +
                "<p>\n" +
                "<button type=\"submit\">확인</button>\n" +
                "</p>\n" +
                "</form>";
    }
}
