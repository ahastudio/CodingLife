package kr.megaptera.makaobank.models;

import kr.megaptera.makaobank.dtos.TransactionDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "sender"))
    private AccountNumber sender;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "receiver"))
    private AccountNumber receiver;

    private Long amount;

    private String name;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Transaction() {
    }

    public Transaction(AccountNumber sender, AccountNumber receiver,
                       Long amount, String name) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.name = name;
    }

    public String activity(AccountNumber currentAccoutNumber) {
        return currentAccoutNumber.equals(sender) ? "송금" : "입금";
    }

    public String name(AccountNumber currentAccoutNumber) {
        return currentAccoutNumber.equals(sender) ? receiver.value() : name;
    }

    public TransactionDto toDto(AccountNumber currentAccoutNumber) {
        return new TransactionDto(
                id,
                activity(currentAccoutNumber),
                name(currentAccoutNumber),
                amount);
    }
}
