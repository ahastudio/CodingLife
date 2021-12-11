// Example #2

// 1. 관심사의 분리 -> 부품들을 결합해서 하나의 프로그램을 만들자.
// 2. 변수(Variable) -> 꾸며주는 게 앞, 이름이 뒤.
//    -> 어떤 값에 이름을 붙여준 것.
// 3. 타입(Type) -> 데이터/값의 종류.
//    1) byte => 8 bits (1 byte) 정수형 => 2^8=256 (0~255) (-128~127)
//    2) short => 16 bits (2 bytes) 정수형 => 2^16=65536
// ** 3) int -> 32 bits (4 bytes) 정수형 -> 소수점 없음.
//    4) long -> 64 bits (8 bytes) 정수형
//    5) float -> 32 bits 부동소수점
//  * 6) double -> 64 bits 부동소수점
//  * 7) boolean -> 불리언 (참/거짓) -> true/false
//    8) char -> 16 bits (2 bytes) 문자
// ** 9) String -> N bytes ... 가변 길이/크기 -> 문자열
// 4. 리터럴(Literal) -> 값을 표현하는 방법.
//    숫자 리터럴 -> 1 2 3
//    문자 리터럴 -> 'H' 'e'
//    문자열 리터럴 -> "Hello"
//    주석(comment) -> //
// 5. 기본 값 (Default) -> 숫자일 경우엔 0, 문자열일 경우에는 "" (빈 문자열)
// 6. Program -> 어떤 일의 순서
// 7. 공백이라는 표현 가능 -> " "
// 8. 문자열을 더하면(+) 어떻게 될까? => 더하기는 없고, 연결을 해준다.

public class HelloName {
    public static void main(String[] args) {
        // 누구에게 인사할까?
        String name = "world";

        // 인사하기 (출력)
        System.out.println("Hello, " + name + "!");
    }
}
