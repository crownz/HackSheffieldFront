package io.mlh.objects.capitalone;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.mlh.mappers.CapitalOneDescriptionSerializer;

import java.math.BigInteger;
import java.util.List;

public class CapitalOneWithdrawal {

    @JsonProperty("_id")
    private String id;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("description")
    private Object description;

    @JsonProperty("medium")
    private String medium;

    @JsonProperty("payer_id")
    private String payerId;

    @JsonProperty("payee_id")
    private String payeeId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("transaction_date")
    private String transactionDate;

    @JsonProperty("type")
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(String payeeId) {
        this.payeeId = payeeId;
    }
}
