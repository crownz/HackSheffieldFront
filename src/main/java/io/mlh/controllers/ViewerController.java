package io.mlh.controllers;

import io.mlh.objects.DisplayMetadata;
import io.mlh.services.CapitalOneService;
import io.mlh.services.SystemStateService;
import io.mlh.types.DataSetType;
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
        ssService.setDisplayData(coService.getAllAccounts());
        ssService.setDisplayMetadata(new DisplayMetadata("chart", true, coService.getAllAccounts().size(), DataSetType.ACCOUNT, "/accounts"));
    }

    @RequestMapping("/metadata")
    public DisplayMetadata getMetadata() {
        return ssService.getDisplayMetadata();
    }

    @RequestMapping("/data")
    public Iterable getData() {
        return ssService.getDisplayData();
    }

}
