// Example #3

// 1. you가 누구냐? -> 입력을 받자.
// 2. Scanner란 “도구(객체)”를 가져다 만들어서 쓸 거다.
// 3. Scanner라는 타입을 가져오기(import). 이 타입은 누군가 만들었다.
// 4. “개”라는 본질. “개”라는 개념은 짖지 않는다. “개”의 개별적인 존재들은 짖는다.
// 5. Scanner는 어딘가에서 값을 가져오는 도구.
// 6. 키보드로 입력 -> System.in (입력)
// 7. new = “create”랑 같은 의미. => 타입을 실체화한다: Class -> Instance.

import java.util.Scanner;

public class HelloToYou {
    public static void main(String[] args) {
        // 1. 입력

        System.out.print("What's your name? ");

        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine();

        // 2. 출력

        System.out.println("Hello, " + name + "!");
    }
}
