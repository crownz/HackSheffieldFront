package io.mlh.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mlh.types.DataSetType;

import java.util.List;

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
    private boolean hideChart;

    @JsonProperty("hideTable")
    private boolean hideTable;

    @JsonProperty("colors")
    private List<String> colors;

    public Metadata(DisplayElementConfig displayElementConfig, boolean changesMade, Integer dataSize, DataSetType requestType, boolean shouldStopPolling, List<String> colors) {
        this.displayElementConfig = displayElementConfig;
        this.changesMadeSinceLastUpdate = changesMade;
        this.dataSize = dataSize;
        this.requestType = requestType;
        this.shouldStopPolling = shouldStopPolling;
        this.hideChart = false;
        this.hideTable = false;
        this.colors = colors;
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

    public boolean isHideTable() {
        return hideTable;
    }

    public void setHideTable(boolean hideTable) {
        this.hideTable = hideTable;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }
}
