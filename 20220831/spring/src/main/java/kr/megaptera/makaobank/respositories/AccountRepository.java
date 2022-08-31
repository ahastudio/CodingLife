package kr.megaptera.makaobank.respositories;

import kr.megaptera.makaobank.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);

    Account save(Account account);
}
