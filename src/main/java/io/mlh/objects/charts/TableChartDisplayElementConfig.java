package io.mlh.objects.charts;

import io.mlh.objects.DisplayElementConfig;
import io.mlh.types.DataSetType;

public class TableChartDisplayElementConfig implements DisplayElementConfig {

    private final String groupedBy;
    private final String sortBy;

    public TableChartDisplayElementConfig(String groupedBy, String sortBy, DataSetType type) {
        this.sortBy = sortBy;
        if (type.equals(DataSetType.ACCOUNT)) {
            this.groupedBy = groupedBy != null ? groupedBy : "type";
        } else if (type.equals(DataSetType.WITHDRAWAL)) {
            this.groupedBy = groupedBy != null ? groupedBy : "description";
        } else {
            throw new IllegalArgumentException("Unsupported data set type!");
        }
    }

    @Override
    public String getType() {
        return "table";
    }

    @Override
    public String getGroupedBy() {
        return this.groupedBy;
    }

    @Override
    public boolean isSummed() {
        return true;
    }

    public String getSorted() {
        return this.sortBy;
    }
}
