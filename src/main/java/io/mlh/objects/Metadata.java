package io.mlh.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mlh.types.DataSetType;

public class Metadata {

    @JsonProperty("changesMadeSinceLastUpdate")
    private boolean changesMadeSinceLastUpdate;

    @JsonProperty("size")
    private Integer dataSize;

    @JsonProperty("requestType")
    private DataSetType requestType;

    @JsonProperty("displayElementConfig")
    private DisplayElementConfig displayElementConfig;


    public Metadata(DisplayElementConfig displayElementConfig, boolean changesMade, Integer dataSize, DataSetType requestType) {
        this.displayElementConfig = displayElementConfig;
        this.changesMadeSinceLastUpdate = changesMade;
        this.dataSize = dataSize;
        this.requestType = requestType;
    }

    public DisplayElementConfig getDisplayElementConfig() {
        return displayElementConfig;
    }

    public void setDisplayElementConfig(DisplayElementConfig chartType) {
        this.displayElementConfig = chartType;
    }

    public boolean isChangesMadeSinceLastUpdate() {
        return changesMadeSinceLastUpdate;
    }

    public void setChangesMadeSinceLastUpdate(boolean changesMadeSinceLastUpdate) {
        this.changesMadeSinceLastUpdate = changesMadeSinceLastUpdate;
    }

    public Integer getDataSize() {
        return dataSize;
    }

    public void setDataSize(Integer dataSize) {
        this.dataSize = dataSize;
    }

    public DataSetType getRequestType() {
        return requestType;
    }

    public void setRequestType(DataSetType requestType) {
        this.requestType = requestType;
    }
}
