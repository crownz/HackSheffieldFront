package io.mlh.utilities;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Map;

public class HttpRequests {

    private HttpRequests() {
    }

    public static JsonNode doGet(String url, Map<String, Object> query) {
        try {
            return Unirest
                    .get(url)
                    .queryString(query)
                    .asJson()
                    .getBody();
        } catch (UnirestException e) {
            throw new IllegalArgumentException("Error executing GET request to " + url);
        }
    }

    public static JsonNode doPost(String url, Map<String, Object> query) {
        try {
            return Unirest
                    .post(url)
                    .queryString(query)
                    .asJson()
                    .getBody();
        } catch (UnirestException e) {
            throw new IllegalArgumentException("Error executing POST request to " + url);
        }
    }

}
