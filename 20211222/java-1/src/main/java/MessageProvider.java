// 1. 같은 이름의 메서드 -> 오버로딩
//    -> 생성자 메서드를 오버로딩했다.
//     = 생성자 메서드가 여러 개다.
// 2. 생성자에서 받은 내용으로 초기화하고, 변경하지 않으면 final 필드를 사용한다.

public class MessageProvider {
    private final String name;

    public MessageProvider() {
        this.name = "world";
    }

    public MessageProvider(String name) {
        this.name = name;
    }

    public String message() {
        return "Hello, " + name + "!";
    }
}
