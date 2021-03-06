package io.mlh.utilities;

import io.mlh.objects.Metadata;
import io.mlh.objects.capitalone.CapitalOneWithdrawal;
import io.mlh.objects.charts.TableChartDisplayElementConfig;
import io.mlh.types.DataSetType;

import javax.el.MethodNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class DisplayDataProcessUtils {

    private String getMethodName;

    private String tempSortedBy;

    public DisplayDataProcessUtils(String groupingKey) {
        if (groupingKey == null) groupingKey = "type";
        this.getMethodName = "get" + groupingKey.substring(0, 1).toUpperCase() + groupingKey.substring(1, groupingKey.length());
    }

    public Object process(Object data, Metadata metadata) {
        if (data == null) return null;

        return processChart(data, metadata);
    }

    private Object processChart(Object data, Metadata metadata) {
        Object result = data;

        if (metadata.getDisplayElementConfig().getType().equals("table")) {
            if (metadata.getRequestType().equals(DataSetType.WITHDRAWAL)) {
                System.out.println("sorting data");

                tempSortedBy = ((TableChartDisplayElementConfig)metadata.getDisplayElementConfig()).getSorted();

                return ((List<CapitalOneWithdrawal>)data)
                        .stream()
                        .sorted(this::sortFn)
                        .collect(Collectors.toList());
            } else {
                return data;
            }
        }

        if (metadata.getDisplayElementConfig().getGroupedBy() != null) {
            result = groupBy((Collection) result);
        }

        result = d3Ify(sumMap((Map<Object, Collection>) result));

        return result;
    }

    private Map<String, List> groupBy(Collection dataSet) {
        return (Map<String, List>)dataSet
                .stream()
                .filter(this::filterNulls)
                .collect(Collectors.groupingBy(this::groupingFn));
    }

    private Map<Object, Integer> sumMap(Map<Object, Collection> dataSet) {
        Map<Object, Integer> result = new HashMap<>();

        dataSet.forEach((Object k, Collection v) -> {

            if (k.getClass() == ArrayList.class) {
                StringBuilder newDescription = new StringBuilder();
                ((ArrayList)k).forEach(newDescription::append);

                result.put(newDescription.toString(), v.size());
            } else {
                result.put(k, v.size());
            }
        });

        return result;
    }

    private List<Map<Object, Object>> d3Ify(Map<Object, Integer> original) {
        List<Map<Object,Object>> result = new ArrayList<>();

        original.forEach((Object k, Integer v) -> {
            Map<Object, Object> r = new HashMap<>();
            r.put("name", k);
            r.put("value", v);
            result.add(r);
        });

        return result;
    }

    private boolean filterNulls (Object obj) {
        try {
            return obj.getClass().getMethod(getMethodName).invoke(obj) != null;
        } catch (Exception e) {
            throw new MethodNotFoundException(e.getCause());
        }
    }


    private int sortFn(CapitalOneWithdrawal obj1, CapitalOneWithdrawal obj2) {
        if (tempSortedBy == null) return 0;

        if (tempSortedBy.contains("amount") || tempSortedBy.contains("ammount")) {
            return obj2.getAmount().compareTo(obj1.getAmount());
        } else if (tempSortedBy.contains("data")) {
            return obj2.getTransactionDate().compareTo(obj1.getTransactionDate());
        } else {
            return 0;
        }
    }

    private Object groupingFn(Object o) {
        try {
            System.out.println(o.getClass().getMethod(getMethodName).invoke(o));
            return o.getClass().getMethod(getMethodName).invoke(o);
        } catch (Exception e) {
            throw new MethodNotFoundException(e.getCause());
        }
    }
}
