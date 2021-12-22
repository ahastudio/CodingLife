// 1. static main -> non-static run
// 2. 입력 (숫자 + 연산자 + 숫자) -> 처리 -> 출력
// 3. 입력은 식(Expression) <1 + 1>
// 4. 가시성 -> public 메서드 + private 필드(멤버 변수, 인스턴스 변수)
// 5. 실패하는 테스트(내가 원하는 걸 기록) -> 뭐라도 만들어 보기.
//    -> 제대로 된 모양으로 고치기(리팩터링).
//    => RED -> GREEN -> REFACTOR
//    => Test-driven Development (TDD, 테스트 주도 개발)
//    -> 결과를 먼저 생각하고, 그 결과를 내기 위해 최선을 다하는 개발.
// 6. 숫자와 연산자 사이를 띄어서 쓴다. (1 + 1 => right, 1+1 => wrong)
// 7. 입력과 출력이라는 비본질적인 부분에서 계산이라는 본질적인 부분을 분리(extract 추출).
//    -> 본질적인 부분에만 집중. -> **관심**
//    -> 관심사의 분리.
// 8. 본질적인 부분에 대한 테스트 코드를 작성. <- 관심사의 분리 때문에 가능.
//    -> 코드를 배치 문제 -> 설계 (응집도를 높이고, 결합도를 낮춘다)
// 9. 추상화 레벨을 맞춰준다.
// 10. 입출력 부분은 라이브러리를 활용하는 경우가 많다. 내가 안 만든다.
//     -> 본질적인 부분은 내가 만든다. => 내 책임. 내가 잘 검증해야 함.
// 11. 본질적인 부분을 재사용할까?
// 12. 가능하면 많은 상황을 예상하고 대응하기!

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Calculator application = new Calculator();
        application.run();
    }

    public void run() {
        // 입력 -> 비본질 #1
        String expression = inputExpression();

        // 처리 -> 본질 -> 비즈니스 로직
        int result = compute(expression);

        // 출력 -> 비본질 #2
        displayResult(result);
    }

    public String inputExpression() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input expression: ");

        return scanner.nextLine();
    }

    public int compute(String expression) {
        String[] words = expression.split(" ");

        int x = Integer.parseInt(words[0]);

        if (words.length == 1) {
            return x;
        }

        int y = Integer.parseInt(words[2]);
        String operator = words[1];

        return switch (operator) {
            case "+" -> x + y;
            case "-" -> x - y;
            case "*" -> x * y;
            case "/" -> x / y;
            default -> 0;
        };
    }

    public void displayResult(int result) {
        System.out.println("Result: " + result);
    }
}
