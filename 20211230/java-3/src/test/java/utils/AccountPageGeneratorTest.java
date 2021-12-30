// 1. account 받아서 생성
// 2. account의 정보 출력 -> 텍스트로... -> HTML

package utils;

import models.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountPageGeneratorTest {
    @Test
    void html() {
        Account account = new Account("1234", "Ashal", 3000);

        PageGenerator pageGenerator = new AccountPageGenerator(account);

        String html = pageGenerator.html();

        assertTrue(html.contains("계좌번호: 1234"), "계좌번호 문제: " + html);
        assertTrue(html.contains("이름: Ashal"), "이름 문제: " + html);
        assertTrue(html.contains("잔액: 3000원"), "잔액 문제: " + html);
    }
}
