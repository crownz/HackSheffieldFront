package io.mlh.utilities;

import io.mlh.objects.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.util.ArrayList;
import java.util.List;

public class CapitalOneEndpointBuilder {

    private final static String baseUrl = "http://api.reimaginebanking.com";
    private String endpoint;
    private List<NameValuePair> params;

    public CapitalOneEndpointBuilder() {
        params = new ArrayList<>();
        endpoint = "";
    }

    public CapitalOneEndpointBuilder withParam(String key, String value) {
        params.add(new NameValuePair(key, value));
        return this;
    }

    public CapitalOneEndpointBuilder withEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public String get() {
        return baseUrl + endpoint + "?" + URLEncodedUtils.format(params, "UTF-8");
    }

    @Override
    public String toString() {
        return get();
    }
}
