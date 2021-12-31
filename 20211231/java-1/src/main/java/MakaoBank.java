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
// 1. 계좌 찾는 부분의 중복 제거
//    - 중복 발견 -> 패턴 -> 중복 제거 => 관심사 분리
//    1) accounts 관리
//    2) account 발견/검색
//    => Repository (저장소) -> 발견/생성
//    -> AccountRepository
// 2. Map을 이용해서 효율 높이기
// 3. 객체의 의존관계: A -> B (A가 B를 사용한다)
//    -> 의존성 주입(DI, dependency injection)

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Map;

import com.sun.net.httpserver.HttpServer;

import models.Account;
import repositories.AccountRepository;
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

    private final String accountIdentifier = "1234";

    private final AccountRepository accountRepository;
    private final TransferService transferService;

    public static void main(String[] args) throws IOException {
        MakaoBank application = new MakaoBank();
        application.run();
    }

    public MakaoBank() {
        formParser = new FormParser();

        accountRepository = new AccountRepository();
        transferService = new TransferService(accountRepository);
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
        Account account = accountRepository.find(identifier, accountIdentifier);
        return new AccountPageGenerator(account);
    }

    private PageGenerator processTransfer(String method,
                                          Map<String, String> formData) {
        if (method.equals("GET")) {
            return processTransferGet();
        }

        return processTransferPost(formData);
    }

    private TransferPageGenerator processTransferGet() {
        Account account = accountRepository.find(accountIdentifier);
        return new TransferPageGenerator(account);
    }

    private TransferSuccessPageGenerator processTransferPost(
            Map<String, String> formData) {
        transferService.transfer(
                accountIdentifier, formData.get("to"),
                Long.parseLong(formData.get("amount")));

        Account account = accountRepository.find(accountIdentifier);
        return new TransferSuccessPageGenerator(account);
    }
}
