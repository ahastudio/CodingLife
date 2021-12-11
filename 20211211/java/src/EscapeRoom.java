// Example #5

// 1. 계속해서 문제 내기 => 반복문 (Flow control) -> while문 (if의 친구)
// 2. 무한 루프 -> while (true)
// 3. 몇 문제 맞췄는지 검사하기 => if문 검사 + 결과 변수 변경
// 4. 증감 -> assign -> reassign
//    assign =>    x = 3
//    reassign =>  x = x + 1
//                 x = 3 + 1
//    single equal vs. double equal
//    x += 1  /  x = x + 1
//    x -= 1
//    x *= 2
//    x /= 2 (재할당) (증감 등 내용 변경)
//    ------
//    x <= y (비교)
//    x >= y

import java.util.Scanner;

public class EscapeRoom {
    public static void main(String[] args) {
        // 0. 준비

        // 도구 준비
        Scanner scanner = new Scanner(System.in);

        // 상태 준비
        int count = 0;

        // 메인 루프 (Main Loop) => 어떻게 종료할까? (종료 조건)
        while (count < 3) {
            // 1. 입력

            System.out.print("Input 2 numbers: ");

            int x = scanner.nextInt();
            int y = scanner.nextInt();

            System.out.println(x + " + " + y + " = ?");

            int answer = scanner.nextInt();

            // 2. 처리

            boolean result = answer == x + y;
            if (result) {
                // 상태 갱신, 변경
                count += 1;
            }

            // 3. 출력

            if (result) {
                System.out.println("You're right! (" + count + ")");
            }

            if (!result) {
                System.out.println("What?");
            }
        }

        // 100. 마무리

        System.out.println("Escape!");
    }
}
