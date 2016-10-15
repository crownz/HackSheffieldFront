package io.mlh.controllers;

import io.mlh.objects.DisplayData;
import io.mlh.services.CapitalOneService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ViewerController {

    private final static Logger logger = LogManager.getLogger(ViewerController.class);

    private final CapitalOneService service;

    @Autowired
    public ViewerController(CapitalOneService service) {
        logger.debug("Initializing " + this.getClass() + "!");
        this.service = service;
    }

    @RequestMapping("/shouldUpdateData")
    public boolean shouldUpdateData() {
        return true;
    }

    @RequestMapping("/getData")
    public DisplayData getData() {
        return new DisplayData("chart", service.getAllAccounts());
    }

}
