package io.mlh.objects.charts;

import io.mlh.objects.DisplayElementConfig;

public class BarChartDisplayElementConfig implements DisplayElementConfig {

    private final String groupedBy;

    public BarChartDisplayElementConfig(String groupedBy) {
        this.groupedBy = groupedBy != null ? groupedBy : "type";
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
