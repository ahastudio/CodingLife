package kr.megaptera.makaobank.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.megaptera.makaobank.models.Account;
import kr.megaptera.makaobank.models.AccountNumber;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(AccountNumber accountNumber);

    Account save(Account account);
}
