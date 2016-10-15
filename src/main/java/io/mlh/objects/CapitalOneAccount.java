package io.mlh.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;

public class CapitalOneAccount {

    @JsonProperty("_id")
    private String id;

    @JsonProperty("balance")
    private BigInteger balance;

    @JsonProperty("customer_id")
    private String customerId;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("rewards")
    private BigInteger rewards;

    @JsonProperty("type")
    private String type;

    @JsonProperty("account_number")
    private String accountNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public BigInteger getRewards() {
        return rewards;
    }

    public void setRewards(BigInteger rewards) {
        this.rewards = rewards;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
