// Example #7

// 숫자 야구 게임
// - 순서가 있는 세 개의 숫자를 준비 (정답) -> 4 8 3
// - 사용자가 세 개의 숫자를 입력 -> 1 2 3
// - 얼마나 잘 맞췄는지 알려줌 -> Strike & Ball
// - Strike: 숫자와 위치가 일치
// - Ball: 숫자만 일치, 위치가 다름.
// - 정답이 4 8 3인데, 1 2 3을 입력한 경우 -> 3이 숫자와 위치가 일치 -> 1 strike
// - 정답이 4 8 3인데, 2 3 4를 입력한 경우 -> 3과 4가 숫자가 일치, 위치가 다름 -> 2 ball
// - 정답이 4 8 3인데, 4 3 2를 입력한 경우 -> 4는 strike, 3은 ball
// - 정답이 4 8 3인데, 3 4 8을 입력 -> 3 ball
// - 정답이 4 8 3인데, 4 3 8을 입력 -> 1 strike 2 ball
// - 정답이 4 8 3인데, 4 8 3을 입력 -> 3 strike => 게임 종료
//
// To do
// 1. 정답을 준비 -> 임의의 숫자 만들기 (random)
// 2. strike와 ball 계산 (연산/처리) -> Business Logic (제일 중요하다)
//
// 1. 중복을 발견 -> 패턴 발견 -> 중복을 제거.
// 2. 배열(Array) -> 같은 타입의 여러 값을 index로 접근해서 관리할 수 있는 타입.
//                  (index는 0부터 시작한다)
// 3. 배열의 크기/길이 => 여러 개 -> 몇 개? length
// 4. main의 args는, 실행할 때 뒤에 쓴 단어(띄어쓰기로 구분)의 갯수.
// 5. 배열의 요소(item, element)에 접근하는 법 -> array[index] => args[0], args[1]
// 6. 배열의 크기를 알고, 안에 있는 걸 모조리 보고 싶다 -> 반복.
// 7. Guard Clause (보호절) -> 실제로 처리해야 하는 상황이 아닌 경우를 처리. => 예외 처리.
// 8. Array 초기화 -> 중괄호를 이용해서 값을 넣을 수 있다.
// 9. 유지보수 -> 유연성, 확장성... 얼마나 나중에 잘 바꿀 수 있는가? => 배열.
// 10. Random이라는 타입을 가져와서 도구를 만들어서 쓴다. (Scanner와 동일)
// 11. 셔플 => 0~9까지의 숫자가 들어있는 배열을 준비하고, 섞어서, 앞에 있는 3개만 사용.
// 12. swap -> temp 변수 사용.
// 13. 복잡한 프로그램 = 복잡한 걸 처리할 수 있다 = 프로그램이 어렵다 = 정답이 없다
//     -> 더 나은 게 있다 = 지금 보는 코드에서 시작 -> 토론 / 질문 / 실험

import java.util.Random;
import java.util.Scanner;

public class BaseballGame {
    public static void main(String[] args) {
        // 0. 준비

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // 정답 준비

        int[] numbers = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        for (int i = 0; i < 20; i += 1) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            int temp = numbers[x];
            numbers[x] = numbers[y];
            numbers[y] = temp;
        }

        for (int i = 0; i < numbers.length; i += 1) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < 100; i += 1) {
            System.out.println();
        }

        boolean playing = true;

        // 메인 루프
        while (playing) {
            // 1. 입력

            System.out.print("Guess numbers: ");

            int[] answers = new int[10];

            for (int i = 0; i < 3; i += 1) {
                answers[i] = scanner.nextInt();
            }

            // 2. 처리 - strike와 ball 개수 계산

            int strike = 0;
            int ball = 0;

            for (int i = 0; i < 3; i += 1) {
                for (int j = 0; j < 3; j += 1) {
                    if (answers[i] != numbers[j]) {
                        continue;
                    }

                    if (i == j) {
                        strike += 1;
                        continue;
                    }

                    ball += 1;
                }
            }

            // 3. 출력

            if (strike != 0) {
                System.out.println(strike + " strike");
            }
            if (ball != 0) {
                System.out.println(ball + " ball");
            }
            if (strike == 0 && ball == 0) {
                System.out.println("No count");
            }

            if (strike == 3) {
                playing = false;
            }
        }

        System.out.println("You win!");
    }
}
