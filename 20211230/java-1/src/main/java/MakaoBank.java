// 1. main -> run
// 2. Web Application Server
//    -> com.sun.net.httpserver 패키지 -> HttpServer 클래스 활용
// 3. 패키지 이름 -> 큰 것부터 작은 것 순서로 나열
//    -> 도메인과는 반대
//       => com.sun.net.httpserver.HttpServer
//       => sun.com이란 도메인을 뒤집은 형태.
//    -> 예: ahastudio.com -> 패키지 이름으로는 com.ahastudio.makao.bank
// 4. URL -> URI
//    http :// localhost : 8000 / home
//    프로토콜(protocol) + 호스트(host) + 포트(port) + 경로(path)
// 5. 웹 브라우저로 확인 -> http://localhost:8000/
// 6. (http) exchange -> 요청을 받거나, 응답을 하는 용도로 사용.
// 7. HTTP Response(응답) -> Header + Body
//    - Header -> content (bytes) size
//    - Body -> content (bytes)
// 8. HTTP 응답 코드
//    - 2xx => 200 (성공), 202 (잘 만들었다)
//    - 3xx => 리다이렉션.
//    - 4xx => 클라이언트 잘못. -> 404 (not found)
//    - 5xx => 서버 잘못. -> 500 (우리/서버 내부에서 큰 일 났다)
// 9. 중복을 발견 -> 중복을 제거 -> 클래스라는 걸 이용 (관심사를 분리)
// 10. MessageGenerator -> 적절한 인사말 만들기
//     -> 처리 완료!
// 11. MessageWriter -> 메시지를 HTTP로 전달
//     -> 처리 완료!
// 12. 요청 URI -> path => 이름 추출
// 13. 3단계 구성: 입력 -> 처리 -> 출력
// 14. 가장 중요한 부분이 어디인지 식별, 분리 -> 3단계 구성 중 “처리”에 해당.
// 15. 3가지 기능:
//     1) 잔액 조회
//     2) 송금
//     3) 거래 내역 확인

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;

import com.sun.net.httpserver.HttpServer;

import utils.MessageGenerator;
import utils.MessageWriter;

public class MakaoBank {
    public static void main(String[] args) throws IOException {
        MakaoBank application = new MakaoBank();
        application.run();
    }

    private void run() throws IOException {
        InetSocketAddress address = new InetSocketAddress(8000);

        HttpServer httpServer = HttpServer.create(address, 0);

        httpServer.createContext("/", (exchange) -> {
            // 1. 입력

            URI requestURI = exchange.getRequestURI();
            String path = requestURI.getPath();

            String name = path.substring(1);

            // 2. 처리

            MessageGenerator messageGenerator = new MessageGenerator(name);
            String content = messageGenerator.text();

            // 3. 출력

            MessageWriter messageWriter = new MessageWriter(exchange);
            messageWriter.write(content);
        });

        httpServer.start();
    }
}
