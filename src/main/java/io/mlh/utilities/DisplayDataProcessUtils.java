package io.mlh.utilities;

import io.mlh.objects.Metadata;

import javax.el.MethodNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class DisplayDataProcessUtils {

    private String getMethodName;

    public DisplayDataProcessUtils(String groupingKey) {
        if(groupingKey == null) throw new IllegalArgumentException("Invalid grouping key!");
        this.getMethodName = "get" + groupingKey.substring(0, 1).toUpperCase() + groupingKey.substring(1, groupingKey.length());
    }

    public Object process(Object data, Metadata metadata) {
        Object result;

        switch(metadata.getChartConfig().getType()) {
            case "pie_chart":
                result = processPieChart(data, metadata);
                break;
            default: throw new IllegalArgumentException("Unsupported chart type!");
        }
        return result;
    }

    private Object processPieChart(Object data, Metadata metadata) {
        Object result = data;

        if (metadata.getChartConfig().getGroupedBy() != null) {
            result = groupBy((Collection) result);
        }

        result = d3Ify(sumMap((Map<String, Collection>) result));

        return result;
    }

    private Map<String, List> groupBy(Collection dataSet) {
        return (Map<String, List>)dataSet
                .stream()
                .collect(Collectors.groupingBy(this::groupingFn));
    }

    private List<Map<String, Object>> d3Ify(Map<String, Integer> original) {
        List<Map<String,Object>> result = new ArrayList<>();

        original.forEach((String k, Integer v) -> {
            Map<String, Object> r = new HashMap<>();
            r.put("name", k);
            r.put("value", v);
            result.add(r);
        });

        return result;
    }

    private Map<String, Integer> sumMap(Map<String, Collection> dataSet) {
        Map<String, Integer> result = new HashMap<>();

        dataSet.forEach((String k, Collection v) -> result.put(k, v.size()));

        return result;
    }

    private Object groupingFn(Object o) {
        try {
            return o.getClass().getMethod(getMethodName).invoke(o);
        } catch (Exception e) {
            throw new MethodNotFoundException(e.getCause());
        }
    }
}
