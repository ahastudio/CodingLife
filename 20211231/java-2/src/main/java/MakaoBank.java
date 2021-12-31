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
// 1. Transaction 모델 만들기
// 2. Account의 transfer에서 transaction 추가하기
//    -> Account는 transactions를 관리해야 함.
//    -> 받은 사람도 거래 내역 추가 돼야 함.
// 3. 커다란 프로그램을 어떻게 관리할까?
//    -> 유지보수 => 새로운 기능 추가 -> 버그 등 문제 해결.
//    -> 관심사의 분리 -> 응집도를 높이고, 결합도(의존성)를 낮추자.
//    => 많이 해보고, 어떤 게 고통스러운지, 어떤 게 나를 돕는지 이해하려고 하자.

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Map;

import com.sun.net.httpserver.HttpServer;

import models.Account;
import repositories.AccountRepository;
import services.TransferService;
import pages.AccountPageGenerator;
import utils.FormParser;
import pages.GreetingPageGenerator;
import utils.MessageWriter;
import pages.PageGenerator;
import utils.RequestBodyReader;
import pages.TransactionsPageGenerator;
import pages.TransferPageGenerator;
import pages.TransferSuccessPageGenerator;

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
            case "transactions" -> processTransactions();
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

    private PageGenerator processTransactions() {
        Account account = accountRepository.find(accountIdentifier);
        return new TransactionsPageGenerator(account);
    }
}
