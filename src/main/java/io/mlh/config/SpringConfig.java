package io.mlh.config;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class SpringConfig {

    private final static Logger logger = LogManager.getLogger(SpringConfig.class);

    public SpringConfig() {
        logger.debug("Initializing SpringConfig!");
    }

}
