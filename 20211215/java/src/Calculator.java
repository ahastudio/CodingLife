// Example #3

// 1. static main, non-static run 메서드 만들기
// 2. JPanel -> 일정한 부분을 따로 만들 수 있음.
// 3. 나머지 구하기 (%) => x % 10 => 0~9
// 4. 정수를 문자열로 변환: Integer.toString(숫자)
// 5. 중복 발견 -> 중복 제거 -> 메서드로! (Extract method)
// 6. int -> 32 bits / long -> 64 bits
// 7. 연산: + - * / =
// 8. for 쉽게 쓰기 -> for (타입 변수 : 배열)
// 9. 사용자 시나리오
//    1) 1을 누르고 3을 누름 -> currentNumber = 13, currentOperator = ""
//    2) +를 누름 -> accumulator = 13, currentNumber = 0, currentOperator = "+"
//    3) 7을 누름 -> accumulator = 13, currentNumber = 7, currentOperator = "+"
//    4) =을 누름 -> accumulator = 20, currentNumber = 0, currentOperator = "="
//    4') +을 누름 -> accumulator = 20, currentNumber = 0, currentOperator = "+"
// 10. 프로그램의 상태
// 11. 문자열 비교 -> String은 객체 -> ==을 못 쓴다. equals 사용.
// 12. Method의 parameter(매개변수)를 이용해서 하나의 메서드를 다르게 작동하게 함.
//     -> 넘겨주는 값은 argument(인자, 인수)라고 부름.
// 13. 최신 Modern Java -> switch (x) { case y -> 블라블라; }
// 14. static -> 사실은 따로 있는 것. 다른 친구들과 같이 쓰려고.
// 15. static final -> constant(상수) -> 대문자로 표기
// 16. 표기법
//     - camelCase -> 전부 소문자, 단어 연결부를 대문자로 표기
//                 -> helloWorld, helloHowAreYouImFineThankYouAndYou
//     - snake_case -> 전부 소문자, 단어 연결부를 밑줄로 나눔
//                  -> hello_world, hello_how_are_you_im_fine_thank_....
//                  -> Java에선 안 씀.
//     - SCREAMING_SNAKE_CASE -> 전부 대문자, 단어 연결부를 밑줄로 나눔.
//                            -> HELLO_WORLD, HELLO_HOW_ARE_YOU_IM_FINE...
//     - PascalCase -> camelCase의 특수한 형태. 첫 글자가 대문자.
//                  -> Class 이름에 사용. HelloWorld, HelloToYou...
// 17. 관심사의 분리 -> **극단적으로** 수행.
// 18. 클래스 단위로 분리!
// 19. 상태는 볼 수 없다(가시성). 행위(method)는 볼 수 있다.
// 20. 꼭 상태를 알고 싶다면, getter를 만들어서 쓴다. (추천은 아니지만 관례)
// 21. 의존성: Calculator -> CoreCalculator (의존성 방향)

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator {
    // GUI 요소
    private JTextField textField;
    private JPanel panel;

    // 핵심 프로그램 요소 (객체)
    private CoreCalculator coreCalculator;

    // Entry point (객체와 무관하게 따로 존재)
    public static void main(String[] args) {
        Calculator application = new Calculator();
        application.run();
    }

    public void run() {
        coreCalculator = new CoreCalculator();

        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField = new JTextField(10);
        updateDisplay(coreCalculator.getCurrentNumber());
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        frame.add(textField, BorderLayout.PAGE_START);

        panel = new JPanel();
        // 영상에는 rows가 4로 나오지만, 5로 해야 제대로 나옵니다.
        panel.setLayout(new GridLayout(5, 3));
        frame.add(panel);

        initNumberButtons();
        initOperatorButtons();

        frame.pack();
        frame.setVisible(true);
    }

    public void initNumberButtons() {
        for (int i = 0; i < 10; i += 1) {
            String number = Integer.toString((i + 1) % 10);
            JButton button = new JButton(number);
            button.addActionListener(event -> {
                coreCalculator.addNumber(number);
                updateDisplay(coreCalculator.getCurrentNumber());
            });
            panel.add(button);
        }
    }

    public void initOperatorButtons() {
        for (String operator : CoreCalculator.OPERATORS) {
            JButton button = new JButton(operator);
            button.addActionListener(event -> {
                coreCalculator.calculate();
                coreCalculator.updateOperator(operator);
                updateDisplay(coreCalculator.getAccumulator());
            });
            panel.add(button);
        }
    }

    public void updateDisplay(String number) {
        textField.setText(number);
    }
}
