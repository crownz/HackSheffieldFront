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

    @JsonProperty("shouldStopPolling")
    private boolean shouldStopPolling;

    @JsonProperty("hideChart")
    private Boolean hideChart;

    @JsonProperty("hideTable")
    private Boolean hideTable;

    public Metadata(DisplayElementConfig displayElementConfig, boolean changesMade, Integer dataSize, DataSetType requestType, boolean shouldStopPolling) {
        this.displayElementConfig = displayElementConfig;
        this.changesMadeSinceLastUpdate = changesMade;
        this.dataSize = dataSize;
        this.requestType = requestType;
        this.shouldStopPolling = shouldStopPolling;
        this.hideChart = false;
        this.hideTable = false;
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

    public boolean shouldStopPolling() {
        return shouldStopPolling;
    }

    public void setShouldStopPolling(boolean shouldStopPolling) {
        this.shouldStopPolling = shouldStopPolling;
    }

    public boolean isHideChart() {
        return hideChart;
    }

    public void setHideChart(boolean hideChart) {
        this.hideChart = hideChart;
    }

    public Boolean isHideTable() {
        return hideTable;
    }

    public void setHideTable(Boolean hideTable) {
        this.hideTable = hideTable;
    }
}
