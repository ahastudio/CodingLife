// 1. HelloWorld 클래스 -> 프로그램 -> main 메서드
// 2. MessageProvider 클래스 -> 메시지 생성 위임 (의존/사용)
//    -> MessageProvider 타입 -> 인스턴스(객체) 만들기 -> 사용하기
// 3. 의존하는 객체를 참조하는 변수를 필드로 옮기기.
// 4. 클래스 파일이 많고, 의존 관계가 복잡한 프로그램은 프로젝트로 만들어서 관리.
//    -> Gradle이라는 도구의 도움을 받는다.

import java.util.Scanner;

public class HelloWorld {
    private MessageProvider messageProvider;

    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.run();
    }

    public void run() {
        System.out.println("[HelloWorld]");

        Scanner scanner = new Scanner(System.in);

        System.out.println("What's your name?");

        String name = scanner.nextLine();

        messageProvider = new MessageProvider(name);

        System.out.println(messageProvider.message());
    }
}
