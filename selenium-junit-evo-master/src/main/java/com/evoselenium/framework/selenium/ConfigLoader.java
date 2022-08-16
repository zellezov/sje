package com.evoselenium.framework.selenium;


import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum ConfigLoader {

    INSTANCE;

    private final Logger LOGGER = LoggerFactory.getLogger(ConfigLoader.class.getName());

    private final Properties properties;

    @NotNull
    public static ConfigLoader get() {
        return INSTANCE;
    }

    ConfigLoader() {
        properties = new Properties();

        try (final InputStream stream = getClass().getClassLoader().getResourceAsStream("test.properties")) {
            properties.load(stream);
        } catch (IOException ex) {
            LOGGER.error("Unable to read properties from 'test.properties' file", ex);
        }
    }

    public String getSeleniumDriver() {
        return properties.getProperty("selenium.driver");
    }

    public String getHostNameUrl() {
        return properties.getProperty("host.name.url");
    }

    public boolean isHeadless() {
        return Boolean.valueOf(properties.getProperty("headless"));
    }

    public String getDriverStayAlive() {
        return properties.getProperty("driver.stay.alive");
    }
}
