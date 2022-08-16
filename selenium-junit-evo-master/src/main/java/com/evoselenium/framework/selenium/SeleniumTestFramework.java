package com.evoselenium.framework.selenium;

import com.evoselenium.framework.selenium.driverfactory.DriverCommunicationsRule;
import com.evoselenium.framework.selenium.driverfactory.DriverInitFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;

import java.util.logging.Logger;

import static java.util.logging.Level.OFF;

public class SeleniumTestFramework {

    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SeleniumTestFramework.class.getName());

    @Rule
    public DriverCommunicationsRule driverCommunicationsRule = new DriverCommunicationsRule(this);

    private WebDriver driver;

    private final TestContext context = new TestContext();

    private ConfigLoader configuration = ConfigLoader.get();

    @Rule
    public TestName testUnderExecution = new TestName();

    @Before
    public void testSetUp() {
        LOGGER.info("| >>>>  Test '" + testUnderExecution.getMethodName() + "' started <<<< |");
        initDriver();
    }

    private void initDriver() {
        Logger.getLogger("org.openqa.selenium.remote").setLevel(OFF);
        driver = DriverInitFactory.getDriver();
        driver.manage().window().maximize();
        driver.get("about:blank");
        driver.get(configuration.getHostNameUrl());
        context.setDriver(driver);
    }

    @After
    public void testTearDown() {
        LOGGER.info("| >>>>  Test '" + testUnderExecution.getMethodName() + "' finished <<<< |");
    }

    protected TestContext getContext() {
        return context;
    }

    public WebDriver getDriver() {
        return context.getDriver();
    }
}
