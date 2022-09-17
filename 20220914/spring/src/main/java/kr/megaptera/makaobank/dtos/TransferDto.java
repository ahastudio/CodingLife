package kr.megaptera.makaobank.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TransferDto {
    @NotBlank
    private String to;

    @NotNull
    private Long amount;

    public String getTo() {
        return to;
    }

    public Long getAmount() {
        return amount;
    }
}
