package com.evoselenium.framework.selenium;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class TestContext {

    private Duration TIMEOUT_DURATION = Duration.ofSeconds(60);

    private Duration POLL_INTERVAL = Duration.ofMillis(200);

    private WebDriver driver;

    public TestContext getContext() {
        return this;
    }

    public WebDriver getDriver() {
        return driver;
    }

    void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public FluentWait<WebDriver> getWait() {
        return new FluentWait<>(driver)
                .withTimeout(TIMEOUT_DURATION)
                .pollingEvery(POLL_INTERVAL)
                .ignoring(StaleElementReferenceException.class, NoSuchElementException.class);
    }
}
