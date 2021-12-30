// 마카오 뱅크의 기능
// 1. 잔액 조회 (/account)
//    -> Account (계좌) -> 계좌 번호 필요함.
// 2. 송금 (/transfer)
//    -> 여러 계좌 -> 어떻게 구분? => 계좌 번호
//               -> 어떻게 관리? => Map
//    -> Transaction 추가.
// 3. 거래 내역 확인 (/transactions)
//    -> Transaction (거래) -> 관리 => List
//
// 1. Account 모델 만들기
// 2. MessageGenerator를 PageGenerator로 이름 바꾸기
//    -> 추상적인 존재로 변경 => 타입
//       -> abstract class -> abstract method => @Override
//    -> HTML을 돌려주게 하기.
// 3. PageGenerator를 상속(extend)해서 AccountPageGenerator 만들기
//    -> 구상/구체를 따로 만들어서 사용.

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;

import com.sun.net.httpserver.HttpServer;

import models.Account;
import utils.AccountPageGenerator;
import utils.GreetingPageGenerator;
import utils.PageGenerator;
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

            // 2. 처리

            PageGenerator pageGenerator = new GreetingPageGenerator();

            if (path.equals("/account")) {
                Account account = new Account("1234", "Ashal", 3000);
                pageGenerator = new AccountPageGenerator(account);
            }

            String content = pageGenerator.html();

            // 3. 출력

            MessageWriter messageWriter = new MessageWriter(exchange);
            messageWriter.write(content);
        });

        httpServer.start();
    }
}
