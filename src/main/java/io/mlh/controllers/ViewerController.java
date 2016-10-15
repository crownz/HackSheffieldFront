package io.mlh.controllers;

import io.mlh.objects.Metadata;
import io.mlh.services.CapitalOneService;
import io.mlh.services.SystemStateService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return ssService.getDisplayMetadata();
    }

    @RequestMapping("/data")
    public Object getData() {
        Metadata md = ssService.getDisplayMetadata();
        if (md != null) {
            md.setChangesMadeSinceLastUpdate(false);
            ssService.setDisplayMetadata(md);
        }
        return ssService.getDisplayData();
    }

}
