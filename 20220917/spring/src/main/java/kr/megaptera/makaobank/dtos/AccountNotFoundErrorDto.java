package kr.megaptera.makaobank.dtos;

public class AccountNotFoundErrorDto extends ErrorDto {
    public AccountNotFoundErrorDto() {
        super(1001, "계좌 번호가 잘못됐습니다");
    }
}
