package io.mlh.controllers;

import io.mlh.objects.DisplayElementConfig;
import io.mlh.objects.Metadata;
import io.mlh.objects.charts.BarChartDisplayElementConfig;
import io.mlh.objects.charts.PieChartDisplayElementConfig;
import io.mlh.objects.charts.TableChartDisplayElementConfig;
import io.mlh.services.CapitalOneService;
import io.mlh.services.SystemStateService;
import io.mlh.types.DataSetType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/alexa")
public class AlexaController {

    private final static Logger logger = LogManager.getLogger(AlexaController.class);

    private final CapitalOneService coService;
    private final SystemStateService ssService;

    @Autowired
    public AlexaController(CapitalOneService coService, SystemStateService ssService) {
        this.coService = coService;
        this.ssService = ssService;
    }

    @RequestMapping("/setMode")
    public void setNewMode(
            @RequestParam String displayElementType,
            @RequestParam String requestType,
            @RequestParam(required=false) String groupedBy) {
        DisplayElementConfig config;

        if(displayElementType.toLowerCase().contains("bar")) {
            config = new BarChartDisplayElementConfig(groupedBy, DataSetType.valueOf(requestType));
        } else if (displayElementType.toLowerCase().contains("pie")) {
            config = new PieChartDisplayElementConfig(groupedBy, DataSetType.valueOf(requestType));
        } else if (displayElementType.toLowerCase().contains("table")) {
            //Change to table config.
            config = new TableChartDisplayElementConfig(groupedBy, DataSetType.valueOf(requestType));
        } else {
            throw new IllegalArgumentException("Invalid displayElementType provided. Only pie,bar charts and table supported");
        }

        List data;

        if (DataSetType.valueOf(requestType).equals(DataSetType.ACCOUNT)) {
            data = coService.getAllAccounts();
        } else if (DataSetType.valueOf(requestType).equals(DataSetType.WITHDRAWAL)){
            data = coService.getAllWithdrawals();
        } else {
            throw new IllegalArgumentException("Unsupported requestType");
        }

        ssService.setDisplayData(data);
        ssService.setDisplayMetadata(new Metadata(
                config,
                true,
                data.size(),
                DataSetType.valueOf(requestType.toUpperCase()),
                false, null));
    }

    @RequestMapping("/colors")
    public void colors(
            @RequestParam String color1,
            @RequestParam String color2,
            @RequestParam(required = false) String color3
    ) {
        List<String> ls = new ArrayList<>();

        ls.add(color1);
        ls.add(color2);
        if (color3 != null) ls.add(color3);

        System.out.println(color1);
        Metadata md = ssService.getDisplayMetadata();

        if (md != null) {
            System.out.println("setting");
            md.setColors(ls);
            ssService.setDisplayMetadata(md);
        }
    }

    @RequestMapping("/hide")
    public void hide(
            @RequestParam(required = false) boolean hideTable,
            @RequestParam(required = false) boolean hideChart
    ) {
        Metadata md = ssService.getDisplayMetadata();

        if (md != null) {
            if (hideTable) md.setHideTable(true); md.setChangesMadeSinceLastUpdate(true);
            if (hideChart) md.setHideChart(true); md.setChangesMadeSinceLastUpdate(true);
        }

        ssService.setDisplayMetadata(md);
    }

    @RequestMapping("/stop")
    public void stop() {
        Metadata md = ssService.getDisplayMetadata();
        if (md != null) {
            md.setShouldStopPolling(true);
            md.setChangesMadeSinceLastUpdate(true);
            ssService.setDisplayMetadata(md);
        }

    }

    @RequestMapping("/reset")
    public void reset() {
        ssService.reset();
    }

    @RequestMapping("/touch")
    public void touch() {
        ssService.setSessionStarted(true);
    }
}
