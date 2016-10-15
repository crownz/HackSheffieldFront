package io.mlh.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.mlh.objects.capitalone.CapitalOneAccount;
import org.json.JSONArray;

import java.util.Arrays;
import java.util.List;

public class AccountMapper {

    private ObjectMapper mapper;

    public AccountMapper() {
        this.mapper = new ObjectMapper();
    }

    public List<CapitalOneAccount> deserializeArray(JSONArray array) {
        try {
            return Arrays.asList(mapper.readValue(array.toString(), CapitalOneAccount[].class));
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not deserialize JSON!", e);
        }
    }


}
