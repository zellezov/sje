package com.evoselenium.framework.selenium.driverfactory;

import org.openqa.selenium.WebDriver;

import java.util.Properties;

public interface IDriverFactory {

    void init(Properties properties);

    WebDriver getWebDriver();
}
