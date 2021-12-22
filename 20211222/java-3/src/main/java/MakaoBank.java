// 1. main -> run
// 2. 입력 -> 처리 -> 출력
// 3. CSV 파일 (엑셀에서 데이터를 텍스트로 내보낼 때)
// 4. 처리하려는 게 뭔가? -> 무언가.. => 객체
//    -> 객체지향 프로그래밍 -> 도메인 모델 (실제 개념 도입)
//    계좌(통장), 거래, 거래 결과
//    선택의 시간
//      1) 계좌가 거래 내역을 가지고 있을 것인가?
//      2) 거래 내역을 따로 관리할 것인가? ==> 이쪽을 선택!
// 5. 계좌 모델, 거래 모델
//    -> Account 만들기!
//    -> Transaction 만들기!
//    -> TransactionResult 만들기!
// 6. null => 객체 없음 -> 위험하기도 하다. => NullPointerException이란 문제 발생!
// 7. 파일에서 데이터 가져오기!!!!!!!
//    Scanner로 입력 받기 -> new Scanner(System.in)
//                     -> new Scanner(파일...)

import models.Account;
import models.Transaction;
import models.TransactionResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MakaoBank {
    public static void main(String[] args) throws IOException {
        MakaoBank application = new MakaoBank();
        application.run();
    }

    private void run() throws IOException {
        // 준비

        Account account = new Account();

        // 입력

        List<Transaction> transactions = loadTransactions();

        // 처리

        List<TransactionResult> transactionResults =
                account.process(transactions);

        // 출력

        saveTransactionResults(transactionResults);
    }

    public List<Transaction> loadTransactions() throws FileNotFoundException {
        List<Transaction> transactions = new ArrayList<>();

        File file = new File("input.csv");

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            Transaction transaction = parseTransaction(line);

            transactions.add(transaction);
        }

        return transactions;
    }

    public Transaction parseTransaction(String text) {
        String[] words = text.split(",");
        int amount = Integer.parseInt(words[1]);
        return new Transaction(words[0], amount);
    }

    public void saveTransactionResults(
            List<TransactionResult> transactionResults) throws IOException {
        FileWriter fileWriter = new FileWriter("output.csv");

        for (TransactionResult transactionResult : transactionResults) {
            String line = transactionResult.toCsvRow();
            fileWriter.write(line + "\n");
        }

        fileWriter.close();
    }
}
