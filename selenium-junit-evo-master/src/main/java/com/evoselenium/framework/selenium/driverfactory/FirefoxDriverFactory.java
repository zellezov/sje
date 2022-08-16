package com.evoselenium.framework.selenium.driverfactory;

import com.evoselenium.framework.selenium.ConfigLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

public class FirefoxDriverFactory extends AbstractDriverFactory implements IDriverFactory {

    @Override
    public void init(Properties properties) {
        super.init(properties);
    }

    @Override
    public WebDriver getWebDriver() {
        System.setProperty("webdriver.gecko.driver", "src/helpingTools/drivers/geckodriver.exe");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

        FirefoxOptions options = new FirefoxOptions()
                .setHeadless(ConfigLoader.get().isHeadless());
        return new FirefoxDriver(options);
    }
}
