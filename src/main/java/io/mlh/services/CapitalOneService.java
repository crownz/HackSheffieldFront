package io.mlh.services;

import com.mashape.unirest.http.JsonNode;
import io.mlh.mappers.AccountMapper;
import io.mlh.mappers.WithdrawalMapper;
import io.mlh.objects.capitalone.CapitalOneAccount;
import io.mlh.objects.capitalone.CapitalOneWithdrawal;
import io.mlh.utilities.CapitalOneEndpointBuilder;
import io.mlh.utilities.HttpRequests;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CapitalOneService {

    private final static Logger logger = LogManager.getLogger(CapitalOneService.class);

    private final String apiKey = "26059d7a49b50d50e556469c846b0d60";

    private final AccountMapper accountMapper;
    private final WithdrawalMapper withdrawalMapper;

    private List<CapitalOneAccount> accounts;
    private List<CapitalOneWithdrawal> withdrawals;

    public CapitalOneService() {
        this.accountMapper = new AccountMapper();
        this.withdrawalMapper = new WithdrawalMapper();
    }

    public List<CapitalOneAccount> getAllAccounts() {
        if (accounts != null) return accounts;

        String url = new CapitalOneEndpointBuilder()
                .withEndpoint("/enterprise/accounts")
                .withParam("key", apiKey)
                .toString();

        logger.info("Making getAllAccounts request to: " + url);

        JsonNode response = HttpRequests.doGet(url, null);
        accounts = accountMapper.deserializeArray((JSONArray) response.getObject().get("results"));
        return accounts;
    }

    public List getAllWithdrawals() {
        if (withdrawals != null) return withdrawals;

        String url = new CapitalOneEndpointBuilder()
                .withEndpoint("/enterprise/withdrawals")
                .withParam("key", apiKey)
                .toString();

        logger.info("Making getAllWithdrawals request to: " + url);

        JsonNode response = HttpRequests.doGet(url, null);
        withdrawals = withdrawalMapper.deserializeArray((JSONArray) response.getObject().get("results"));
        return withdrawals;
    }
}
