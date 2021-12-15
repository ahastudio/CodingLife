// Example #2

// 1. main 메서드 만들기
// 2. HelloToYou 타입의 객체 만들기 -> run 메서드 활용
// 3. JFrame -> window 띄우기
// 4. JLabel -> 인사 텍스트 보여주기
// 5. JTextField -> 입력 컨트롤(control)
// 6. 마지막 컨트롤만 보이는 문제! -> 레이아웃 (Flow, Grid)
// 7. JButton -> 버튼 컨트롤 -> Action(버튼 누름) -> Event => Listener
// 8. Lambda: 매개변수(parameters) -> { ... }
// 9. 변수의 종류:
//    1) 지역 변수 => 임시 변수
//    2) 매개 변수 (parameters)
//    3) 멤버 변수 (Instance/Object Field) => 객체와 생명 주기(Life Cycle)를 같이 한다.
//       --> 무조건 private. public을 할 수도 있지만, 불법!
// 10. 중복을 발견 -> 패턴 발견 -> 중복 제거
// 11. Refactor 도구 -> Extract method
// 12. Method: public/private + static/non-static + 반환 타입(return type) + 이름
//     -> 추상화(abstraction) => 중복 제거/재사용 + 이름 붙이기
//     -> 관심사의 분리

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class HelloToYou {
    private String name = "world";

    public static void main(String[] args) {
        HelloToYou application = new HelloToYou();
        application.run();
    }

    public void run() {
        JFrame frame = new JFrame("Hello to you");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 1));
        frame.setSize(400, 200);

        JLabel label = new JLabel(greetingMessage());
        frame.add(label);

        JTextField textField = new JTextField(10);
        frame.add(textField);

        JButton button = new JButton("확인");
        button.addActionListener(event -> {
            name = textField.getText();
            label.setText(greetingMessage());
        });
        frame.add(button);

        frame.setVisible(true);
    }

    public String greetingMessage() {
        return "Hello, " + name + "!";
    }
}
