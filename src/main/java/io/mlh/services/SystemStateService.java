package io.mlh.services;

import org.springframework.stereotype.Service;

@Service
public class SystemStateService {

    /**
     * If alexa has updated changes and the front end has not pulled since,
     * then the variable is set to true.
     */
    private boolean changesHaveBeenMade;


    public SystemStateService() {
        changesHaveBeenMade = true;
    }



}
