package io.mlh.controllers;

import io.mlh.objects.Metadata;
import io.mlh.services.CapitalOneService;
import io.mlh.services.SystemStateService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ViewerController {

    private final static Logger logger = LogManager.getLogger(ViewerController.class);

    private final SystemStateService ssService;

    @Autowired
    public ViewerController(CapitalOneService coService, SystemStateService ssService) {
        logger.debug("Initializing " + this.getClass() + "!");
        this.ssService = ssService;
    }

    @RequestMapping("/metadata")
    public Metadata getMetadata() {
        Metadata md = ssService.getDisplayMetadata();

        if (md != null) {
            Metadata newMd = new Metadata(
                    md.getDisplayElementConfig(),
                    false,
                    md.getDataSize(),
                    md.getRequestType(),
                    md.shouldStopPolling(),
                    null
            );

            if (newMd.shouldStopPolling()) newMd.setShouldStopPolling(false);

            ssService.setDisplayMetadata(newMd);
        }

        return md;
    }

    @RequestMapping("/data")
    public Object getData() {
        return ssService.getDisplayData();
    }

    @RequestMapping("/session")
    public Map hasBeenTouched() {
        Map<String, Boolean> result = new HashMap<>();
        result.put("hasBeenTouched", ssService.isSessionStarted());
        return result;
    }
}
