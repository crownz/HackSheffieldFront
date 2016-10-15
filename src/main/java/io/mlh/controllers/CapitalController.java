package io.mlh.controllers;

import io.mlh.objects.CapitalOneAccount;
import io.mlh.services.CapitalOneService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/capital")
public class CapitalController {

    private final static Logger logger = LogManager.getLogger(CapitalController.class);

    private final CapitalOneService service;

    @Autowired
    public CapitalController(CapitalOneService service) {
        logger.debug("Initializing " + this.getClass() + "!");
        this.service = service;
    }

    @RequestMapping("/accounts/all")
    public List<CapitalOneAccount> allAccounts() {
        List<CapitalOneAccount> resp = service.getAllAccounts();
        logger.info(resp);
        return resp;
    }

}
