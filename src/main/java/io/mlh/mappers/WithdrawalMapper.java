package io.mlh.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.mlh.objects.capitalone.CapitalOneAccount;
import io.mlh.objects.capitalone.CapitalOneWithdrawal;
import org.json.JSONArray;

import java.util.Arrays;
import java.util.List;

public class WithdrawalMapper {

    private ObjectMapper mapper;

    public WithdrawalMapper() {
        this.mapper = new ObjectMapper();
    }

    public List<CapitalOneWithdrawal> deserializeArray(JSONArray array) {
        try {
            return Arrays.asList(mapper.readValue(array.toString(), CapitalOneWithdrawal[].class));
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not deserialize JSON!", e);
        }
    }


}
