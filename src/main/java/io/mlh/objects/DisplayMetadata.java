package io.mlh.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mlh.types.DataSetType;

public class DisplayMetadata {

    @JsonProperty("displayMode")
    private String displayMode;

    @JsonProperty("changesMadeSinceLastUpdate")
    private boolean changesMadeSinceLastUpdate;

    @JsonProperty("dataSetSize")
    private Integer dataSetSize;

    @JsonProperty("dataSetType")
    private DataSetType dataSetType;

    @JsonProperty("capitalOneEndpoint")
    private String capitalOneEndpoint;

    public DisplayMetadata(String displayMode, boolean changesMade, Integer dataSetSize, DataSetType dataSetType, String capitalOneEndpoint) {
        this.displayMode = displayMode;
        this.changesMadeSinceLastUpdate = changesMade;
        this.dataSetSize = dataSetSize;
        this.dataSetType = dataSetType;
        this.capitalOneEndpoint = capitalOneEndpoint;
    }

    public String getDisplayMode() {
        return displayMode;
    }

    public void setDisplayMode(String displayMode) {
        this.displayMode = displayMode;
    }

    public boolean isChangesMadeSinceLastUpdate() {
        return changesMadeSinceLastUpdate;
    }

    public void setChangesMadeSinceLastUpdate(boolean changesMadeSinceLastUpdate) {
        this.changesMadeSinceLastUpdate = changesMadeSinceLastUpdate;
    }

    public Integer getDataSetSize() {
        return dataSetSize;
    }

    public void setDataSetSize(Integer dataSetSize) {
        this.dataSetSize = dataSetSize;
    }

    public DataSetType getDataSetType() {
        return dataSetType;
    }

    public void setDataSetType(DataSetType dataSetType) {
        this.dataSetType = dataSetType;
    }

    public String getCapitalOneEndpoint() {
        return capitalOneEndpoint;
    }

    public void setCapitalOneEndpoint(String capitalOneEndpoint) {
        this.capitalOneEndpoint = capitalOneEndpoint;
    }
}
