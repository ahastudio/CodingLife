package kr.megaptera.makaobank.dtos;

public class TransactionDto {
    private Long id;

    private String activity;

    private String name;

    private Long amount;

    public TransactionDto() {
    }

    public TransactionDto(Long id, String activity, String name, Long amount) {
        this.id = id;
        this.activity = activity;
        this.name = name;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public String getActivity() {
        return activity;
    }

    public String getName() {
        return name;
    }

    public Long getAmount() {
        return amount;
    }
}
