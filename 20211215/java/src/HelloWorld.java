// Example #1

// 1. main 메서드 만들기
// 2. GUI (Graphic User Interface) -> Swing (JFC)
// 3. Library -> 누군가 만들어 놓은 걸 가져다 쓸 수 있다.
// 4. 타입, 인스턴스 -> 특정 타입의 *객체*(Object) => 무언가
// 5. JFrame (Java/Swing + Frame) -> Window
// 6. Primitive Type (숫자에 가까운 것들) + Object Type (String, Scanner 등)
// 7. 메서드 -> public/private(가시성) + static/[없거나] + 반환 타입(void) + 메서드 이름
// 8. 어떤 작업을 추상화(abstraction) -> 이름 붙이기
// 9. Extract Method -> 메서드로 추출하기
// 10. Refactor 도구 활용 => Refactoring (결과가 바뀌지 않고 코드만 고친다)

import javax.swing.JFrame;
import javax.swing.JLabel;

public class HelloWorld {
    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.run();
    }

    public void run() {
        JFrame frame = new JFrame("Hello, world!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JLabel label = new JLabel("Hello!");
        frame.add(label);

        frame.setVisible(true);
    }
}
