package io.mlh.objects.charts;

import io.mlh.objects.DisplayElementConfig;

public class PieDisplayElementConfig implements DisplayElementConfig {

    private final String groupedBy;

    public PieDisplayElementConfig(String groupedBy) {
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
