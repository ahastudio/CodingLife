package kr.megaptera.makaobank.repositories;

import kr.megaptera.makaobank.models.AccountNumber;
import kr.megaptera.makaobank.models.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class TransactionRepositoryTest {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void findAllBySenderOrReceiver() {
        AccountNumber sender = new AccountNumber("1234");
        AccountNumber receiver = new AccountNumber("5678");
        Long amount = 100_000L;
        String name = "Test";
        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.execute("DELETE FROM transaction");

        jdbcTemplate.update("" +
                        "INSERT INTO transaction(" +
                        "   id, sender, receiver, amount, name," +
                        "   created_at, updated_at" +
                        ")" +
                        " VALUES(1, ?, ?, ?, ?, ?, ?)",
                sender.value(), receiver.value(), amount, name, now, now
        );

        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(0, 100, sort);
        List<Transaction> transactions =
                transactionRepository.findAllBySenderOrReceiver(
                        sender, sender, pageable);

        assertThat(transactions).hasSize(1);

        assertThat(transactions.get(0).activity(sender)).isEqualTo("송금");
    }

    @Test
    void save() {
        AccountNumber sender = new AccountNumber("1234");
        AccountNumber receiver = new AccountNumber("5678");
        Long amount = 100_000L;
        String name = "Test";

        Transaction transaction = new Transaction(
                sender, receiver, amount, name);

        transactionRepository.save(transaction);
    }
}
