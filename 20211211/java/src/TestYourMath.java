// Example #4

// 요구사항
// - 숫자 두 개를 입력 받음. => 1 3
// - 숫자 두 개의 합을 입력하라고 요구함. => 1 + 3 = ?
// - 숫자 두 개의 곱을 입력하라고 요구함. => 1 * 3 = ?
// - 답을 입력하면 Genius!, Muggle!, Stupid!를 출력.
//   -> 둘 다 맞추면 천재, 하나만 맞추면 머글, 둘 다 틀리면 바보.
//
// 1. boolean -> 참(true) 또는 거짓(false)
// 2. assign -> single equal (=) => 변수에 값을 지정 / 값에 이름을 붙여줌.
//    -> 다른 언어(Pascal)에선 := 등을 사용. Lisp 등은 define 같은 걸 활용.
// 3. compare -> double equal (==) => 두 값이 같은지 비교.
//    -> 다른 언어(Pascal, Lisp)에선 = 등을 사용.
// 4. 연산자 우선순위
//    1) 높음: * /
//    2) 낮아진다: + -
//    3) Compare: == != < > <= >=
//    4) AND: &&
//    4) OR: ||
//    5) 진짜 낮음: =
// 5. Flow Control (조건에 따라 다르게 행동) -> 조건문 (if문 = 만약 ~라면 ~해라)
// 6. 논리연산자 => AND(&&) OR(||)
// 7. 비트연산자 => AND(&) OR(|) => 먼저 선점. 아까비!
// 8. 중복을 발견 => 패턴 발견 => 중복을 제거.
// 9. 참과 거짓을 반대로 할 순 없나? => NOT(!)

import java.util.Scanner;

public class TestYourMath {
    public static void main(String[] args) {
        // 0. 준비

        Scanner scanner = new Scanner(System.in);

        // 1. 입력

        System.out.print("Input 2 numbers: ");

        int x = scanner.nextInt();
        int y = scanner.nextInt();

        System.out.println(x + " + " + y + " = ?");

        int answer1 = scanner.nextInt();

        System.out.println(x + " * " + y + " = ?");

        int answer2 = scanner.nextInt();

        // 2. 연산/처리

        boolean result1 = answer1 == x + y;
        boolean result2 = answer2 == x * y;

        // 3. (결과) 출력

        if (result1 && result2) {
            System.out.println("Genius!");
        }

        if ((result1 && !result2) || (!result1 && result2)) {
            System.out.println("Muggle!");
        }

        if (!result1 && !result2) {
            System.out.println("Stupid!");
        }
    }
}
