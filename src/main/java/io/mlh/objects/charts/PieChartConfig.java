package io.mlh.objects.charts;

import io.mlh.objects.ChartConfig;

public class PieChartConfig implements ChartConfig {

    private final String groupedBy;

    public PieChartConfig(String groupedBy) {
        this.groupedBy = groupedBy;
    }

    @Override
    public String getType() {
        return "pie_chart";
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
