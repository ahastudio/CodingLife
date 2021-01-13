// TDD Cycle
// 1. Red -> 실패하는 것 만든다.
// 2. Green -> 엄청 빨리 통과시킨다.
// 3. Refactoring -> 의도를 드러낸다. => 중복 제거. => Clean Code

// ToDo
// 1. $5 * 2 = $10 => 이거 하자! => 완료
// 2. $5 + 10CHF = $10 (환율이 USD:CHF=2:1) ====> 이걸 해야겠네? => 완료
// 3. public field => 해결하고 싶다. -> 객체를 비교하면 어떨까? => VO => 완료
// 4. Equal => 완료
// 5. toString => 완료
// 6. Dollar 형 변환 실패? ==> Money가 아닌 경우로 다시! => 완료!!!
// 7. $5 + $5 = $10 => 완료
// 8. 5CHF * 2 = 10CHF => 완료
// 8. 5CHF + 5CHF = 10CHF => 완료
// 9. 복붙은 중복을 낳았다!!!!!!!! (죄악) => 제일 긴 Refactoring을 통해 죄악을 해결했다.
// 10. Expression + Reduce => 완료
// 11. Sum => 간단히 만들었다.
// 12. Reduce (w/ Currency + Bank) => 완료
// 13. 환율은 어떻게 관리? => Bank => 완료
// 14. sum을 sum하면??? => 완료

package com.ahastudio.money;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MoneyTest {
    private Bank bank;

    @BeforeEach
    void prepareBank() {
        bank = new Bank();
        bank.setRate("USD", "CHF", 2.0);
        bank.setRate("CHF", "USD", 0.5);
    }

    @Test
    void multiplication() {
        assertThat(Money.dollar(5).times(2)).isEqualTo(Money.dollar(10));

        assertThat(Money.franc(5).times(2)).isEqualTo(Money.franc(10));
    }

    @Test
    void equal() {
        assertThat(Money.dollar(5)).isEqualTo(Money.dollar(5));
        assertThat(Money.dollar(5)).isNotEqualTo(Money.dollar(6));

        assertThat(Money.franc(5)).isEqualTo(Money.franc(5));
        assertThat(Money.franc(5)).isNotEqualTo(Money.franc(6));

        assertThat(Money.dollar(5)).isNotEqualTo("Hello, world!");
    }

    @Test
    void string() {
        assertThat(Money.dollar(5).toString()).isEqualTo("USD(5.0)");

        assertThat(Money.franc(5).toString()).isEqualTo("CHF(5.0)");
    }

    @Test
    void plusDollar() {
        Money five = Money.dollar(5);
        Money ten = Money.dollar(10);
        assertThat(five.plus(five)).isEqualTo(ten);
    }

    @Test
    void plusFranc() {
        Money five = Money.franc(5);
        Money ten = Money.franc(10);
        assertThat(five.plus(five)).isEqualTo(ten);
    }

    @Test
    void sum() {
        Money dollar = Money.dollar(5);
        Money franc = Money.franc(10);

        assertThat(dollar.sum(dollar).reduce("USD", bank))
                .isEqualTo(Money.dollar(10));
        assertThat(franc.sum(franc).reduce("CHF", bank))
                .isEqualTo(Money.franc(20));

        assertThat(dollar.sum(dollar).reduce("CHF", bank))
                .isEqualTo(Money.franc(20));

        assertThat(dollar.sum(franc).reduce("USD", bank))
                .isEqualTo(Money.dollar(10));
        assertThat(franc.sum(dollar).reduce("USD", bank))
                .isEqualTo(Money.dollar(10));

        assertThat(dollar.sum(franc).reduce("CHF", bank))
                .isEqualTo(Money.franc(20));
        assertThat(franc.sum(dollar).reduce("CHF", bank))
                .isEqualTo(Money.franc(20));
    }

    @Test
    void reduce() {
        assertThat(Money.dollar(5).reduce("USD", bank))
                .isEqualTo(Money.franc(5));
        assertThat(Money.dollar(5).reduce("CHF", bank))
                .isEqualTo(Money.franc(10));
        assertThat(Money.franc(10).reduce("USD", bank))
                .isEqualTo(Money.dollar(5));
    }

    @Test
    void manySum() {
        Money five = Money.dollar(5);

        assertThat(five.sum(five).sum(five).reduce("CHF", bank))
                .isEqualTo(Money.franc(30));
    }
}
