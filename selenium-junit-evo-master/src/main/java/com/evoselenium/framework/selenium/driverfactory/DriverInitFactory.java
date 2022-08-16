package com.evoselenium.framework.selenium.driverfactory;

import com.evoselenium.framework.selenium.ConfigLoader;
import org.openqa.selenium.WebDriver;

public class DriverInitFactory {

    public static WebDriver getDriver() {
        String driverName = ConfigLoader.get().getSeleniumDriver();
        switch (driverName) {
            case "driver.firefox":
                return new FirefoxDriverFactory().getWebDriver();
            case "driver.chrome":
                return new ChromeDriverFactory().getWebDriver();
        }
        throw new IllegalStateException("Unknown driver: " + driverName);
    }
}
