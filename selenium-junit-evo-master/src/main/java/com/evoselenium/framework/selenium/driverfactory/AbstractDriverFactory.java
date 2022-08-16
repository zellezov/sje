package com.evoselenium.framework.selenium.driverfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public abstract class AbstractDriverFactory implements IDriverFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDriverFactory.class.getName());

    @Override
    public void init(Properties properties) {
        LOGGER.info(getClass().getSimpleName() + " initialized");
    }
}
