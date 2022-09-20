package kr.megaptera.makaobank.dtos;

public class LoginResultDto {
    private final String accessToken;

    private final String name;

    private final Long amount;

    public LoginResultDto(String accessToken, String name, Long amount) {
        this.accessToken = accessToken;
        this.name = name;
        this.amount = amount;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getName() {
        return name;
    }

    public Long getAmount() {
        return amount;
    }
}
