// 1. 거래 정보 만들기 => 완료!
// 2. 거래 정보 비교하기 -> 완료!
//    -> 그런데 테스트 결과에서 내용이 잘 보이면 좋겠네?! => 완료!
// 3. 잔액 변동? - TODO
// 4. 잔액 변하게 한 결과 도출? - TODO
//
// 비교할 때 두 가지 개념
// 1. 동일 -> == (둘이 같은 객체다) (Primitive Type은 그냥 값이 같다)
// 2. 동등 -> equals (둘이 그냥 같다고 취급하면 좋은데?) (객체를 값으로 취급) (값 객체, Value Object)
//        -> equals라는 메서드를 [오버라이딩].
//
// toString -> 이 객체의 정보를 내가 원하는 문자열로 출력할 수 있음.

package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionTest {
    @Test
    void creation() {
        Transaction transaction = new Transaction("입금", 1000);
    }

    @Test
    void equals() {
        Transaction transaction1 = new Transaction("입금", 1000);
        Transaction transaction2 = new Transaction("입금", 1000);

        assertEquals(transaction1, transaction2);
    }

    @Test
    void string() {
        Transaction transaction = new Transaction("입금", 1000);

        assertEquals("Transaction(입금: 1000)", transaction.toString());
    }

    @Test
    void process() {
        assertEquals(1000, new Transaction("잔액", 1000).process(0));
        assertEquals(1000, new Transaction("잔액", 1000).process(100));

        assertEquals(1000, new Transaction("입금", 1000).process(0));
        assertEquals(1100, new Transaction("입금", 1000).process(100));

        assertEquals(900, new Transaction("출금", 100).process(1000));

        assertEquals(1000, new Transaction("오류", 100).process(1000));
    }
}
