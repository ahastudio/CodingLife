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
// 1. TransferPageGenerator -> HTML => 송금 UI => <form> 만들기
// 2. 송금 처리 => POST
//    1) /transfer => 같은 주소 활용 (V) => method 확인 필요.
//    2) /transfer-process => 다른 주소 사용
// 3. 송금 결과
//    1) TransferSuccessPageGenerator
//    2) TransferFailPageGenerator
// 4. Template method pattern 활용
// 5. GET과 POST 처리 나누기.
//    -> 삼항연산자 활용 => 조건 ? true일 때 : false일 때
//    -> 처리하는 부분과 결과 보여주는 부분도 나눔.
// 6. TransferService -> 관심사의 분리.
// 7. List 같은 Collection을 다룰 때 무식하게 할 수 있다.
//    -> 더 나은 방법은 없을까?
//    -> Stream API
//       => items란 List가 있으면...
//          items.stream() => Stream => filter, map, reduce...
// 8. POST로 받을 수 있는 것 -> Form Data => Request Body.
//    -> to = 다른 사람 계좌 번호 & amount = 송금할 금액
//    -> key-value => Pair => 여럿.
//    -> key가 to인 쌍과 key가 amount인 쌍이 존재.
// 9. FormParser <- RequestBodyReader
// 10. Key-Value로 된 Pair가 여럿일 때 뭐로 관리할 수 있을까?
//     Value가 여럿일 때 뭐로 관리할 수 있을까? -> List -> ArrayList
//     마찬가지로 Pair가 여럿이면... -> Map -> HashMap
// 11. Stream API를 쓰기 위한 준비
//     - List -> .stream
//     - String[] -> Arrays.stream
// 12. 다른 사람 잔액도 조회
//     -> /account/계좌번호

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.List;
import java.util.Map;

import com.sun.net.httpserver.HttpServer;

import models.Account;
import services.TransferService;
import utils.AccountPageGenerator;
import utils.FormParser;
import utils.GreetingPageGenerator;
import utils.MessageWriter;
import utils.PageGenerator;
import utils.RequestBodyReader;
import utils.TransferPageGenerator;
import utils.TransferSuccessPageGenerator;

public class MakaoBank {
    private final FormParser formParser;

    private final List<Account> accounts;
    private final Account account;
    private final TransferService transferService;

    public static void main(String[] args) throws IOException {
        MakaoBank application = new MakaoBank();
        application.run();
    }

    public MakaoBank() {
        formParser = new FormParser();

        accounts = List.of(
                new Account("1234", "Ashal", 3000),
                new Account("2345", "JOKER", 1000)
        );
        account = accounts.get(0);

        transferService = new TransferService(accounts);
    }

    private void run() throws IOException {
        InetSocketAddress address = new InetSocketAddress(8000);

        HttpServer httpServer = HttpServer.create(address, 0);

        httpServer.createContext("/", (exchange) -> {
            // 1. 입력

            URI requestURI = exchange.getRequestURI();
            String path = requestURI.getPath();

            String method = exchange.getRequestMethod();

            String requestBody = new RequestBodyReader(exchange).body();

            Map<String, String> formData = formParser.parse(requestBody);

            // 2. 처리

            PageGenerator pageGenerator = process(path, method, formData);

            // 3. 출력

            new MessageWriter(exchange).write(pageGenerator.html());
        });

        httpServer.start();

        System.out.println("Server is listening... http://localhost:8000/");
    }

    public PageGenerator process(String path, String method,
                                 Map<String, String> formData) {
        String[] steps = path.substring(1).split("/");
        return switch (steps[0]) {
            case "account" -> processAccount(steps.length > 1 ? steps[1] : "");
            case "transfer" -> processTransfer(method, formData);
            default -> new GreetingPageGenerator();
        };
    }

    private AccountPageGenerator processAccount(String identifier) {
        Account found = accounts.stream()
                .filter(account -> account.identifier().equals(identifier))
                .findFirst()
                .orElse(account);
        return new AccountPageGenerator(found);
    }

    private PageGenerator processTransfer(String method,
                                          Map<String, String> formData) {
        if (method.equals("GET")) {
            return processTransferGet();
        }

        return processTransferPost(formData);
    }

    private TransferPageGenerator processTransferGet() {
        return new TransferPageGenerator(account);
    }

    private TransferSuccessPageGenerator processTransferPost(
            Map<String, String> formData) {
        transferService.transfer(
                account.identifier(), formData.get("to"),
                Long.parseLong(formData.get("amount")));

        return new TransferSuccessPageGenerator(account);
    }
}
