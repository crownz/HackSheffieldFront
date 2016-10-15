package io.mlh.controllers;

import io.mlh.objects.DisplayElementConfig;
import io.mlh.objects.Metadata;
import io.mlh.objects.charts.BarChartDisplayElementConfig;
import io.mlh.objects.charts.PieChartDisplayElementConfig;
import io.mlh.services.CapitalOneService;
import io.mlh.services.SystemStateService;
import io.mlh.types.DataSetType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        List lAcc = coService.getAllAccounts();
        DisplayElementConfig config;

        if(displayElementType.contains("bar")) {
            config = new BarChartDisplayElementConfig(groupedBy);
        } else if (displayElementType.contains("pie")) {
            config = new PieChartDisplayElementConfig(groupedBy);
        } else if (displayElementType.contains("table")) {
            //Change to table config.
            config = new PieChartDisplayElementConfig(groupedBy);
        } else {
            throw new IllegalArgumentException("Invalid displayElementType provided. Only pie,bar charts and table supported");
        }

        
        ssService.setDisplayData(lAcc);
        ssService.setDisplayMetadata(new Metadata(config, true, lAcc.size(), DataSetType.valueOf(requestType.toUpperCase())));
    }
}
