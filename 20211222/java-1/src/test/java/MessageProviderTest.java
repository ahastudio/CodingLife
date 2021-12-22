// 1. 패키지는 도메인을 뒤집은 모양으로 작성한다.
//   - 도메인
//     - lecture.ahastudio.com
//       하위 <----------> 최상위
//   - 패키지는 최상위부터 쓴다.
//     - com.ahastudio.lecture
//     -> org.junit.jupiter.api.....
// 2. JUnit의 테스트 코드는 특별히 다르게 실행해야 한다.
//    -> ./gradlew test
//    -> build.gradle 파일의 “test” Task 확인!
// 3. 원하는 결과를 테스트 코드에 써줄 수 있다.
//    -> 단언문 (Assertion)
//       assertEquals(expected = 원하는 결과, actual = 내가 실행하는 것)
// 4. @Test 어노테이션이 붙은 메서드를 테스트할 때 실행.
//    -> assertXXX(여기에선 assertEquals)를 사용해서 검증.
// 5. 확인 작업을 자동으로 해주는 JUnit이란 프레임워크를 사용.
//    -> 테스트 자동화

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageProviderTest {
    @Test
    void defaultMessage() {
        MessageProvider messageProvider = new MessageProvider();

        assertEquals("Hello, world!", messageProvider.message());
    }

    @Test
    void messageToYou() {
        MessageProvider messageProvider = new MessageProvider("Ashal");

        assertEquals("Hello, Ashal!", messageProvider.message());
    }
}
