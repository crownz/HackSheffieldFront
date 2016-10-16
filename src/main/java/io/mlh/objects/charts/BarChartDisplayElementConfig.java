package io.mlh.objects.charts;

import io.mlh.objects.DisplayElementConfig;
import io.mlh.types.DataSetType;

public class BarChartDisplayElementConfig implements DisplayElementConfig {

    private final String groupedBy;

    public BarChartDisplayElementConfig(String groupedBy, DataSetType type) {
        if (type.equals(DataSetType.ACCOUNT)) {
            this.groupedBy = groupedBy != null ? groupedBy : "type";
        } else if (type.equals(DataSetType.WITHDRAWAL)) {
            this.groupedBy = groupedBy != null ? groupedBy : "amount";
        } else {
            throw new IllegalArgumentException("Unsupported data set type!");
        }
    }

    @Override
    public String getType() {
        return "bar_chart";
    }

    @Override
    public String getGroupedBy() {
        return this.groupedBy;
    }

    @Override
    public boolean isSummed() {
        return true;
    }

}
