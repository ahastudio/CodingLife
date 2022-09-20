package kr.megaptera.makaobank.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TransferDto {
    @NotBlank
    private String to;

    @NotNull
    private Long amount;

    @NotBlank
    private String name;

    public String getTo() {
        return to;
    }

    public Long getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }
}
