package io.mlh.controllers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alexa")
public class AlexaController {

    private final static Logger logger = LogManager.getLogger(AlexaController.class);

    public AlexaController() {

    }

    @RequestMapping("/setMode")
    public void setNewMode(@RequestParam String displayMode) {
        logger.error("Called alexa setMode!");
    }

    //Request type
    //Display mode - chart / table
        //If chart - pie or bar
            //Pie chart - grouped by field name
}
