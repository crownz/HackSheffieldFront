package io.mlh.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DisplayData {

    /**
     * Type of data - chart, map, etc.
     */
    @JsonProperty("type")
    private String type;

    @JsonProperty("data")
    private Object data;

    public DisplayData() {

    }

    public DisplayData(String type, Object data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
