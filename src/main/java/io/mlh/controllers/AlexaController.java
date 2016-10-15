package io.mlh.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alexa")
public class AlexaController {

    public AlexaController() {

    }

    @RequestMapping("/setMode")
    public void setNewMode(@RequestParam String displayMode) {

    }

    //Request type
    //Display mode - chart / table
        //If chart - pie or bar
            //Pie chart - grouped by field name
}
