package com.evoselenium.framework.selenium.driverfactory;

import com.evoselenium.framework.selenium.ConfigLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Properties;

public class ChromeDriverFactory extends AbstractDriverFactory implements IDriverFactory {

    @Override
    public void init(Properties properties) {
        super.init(properties);
    }

    @Override
    public WebDriver getWebDriver() {
        System.setProperty("webdriver.chrome.driver", "src/helpingTools/drivers/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        if (ConfigLoader.get().isHeadless()) {
            options.addArguments("--headless");
        }
        return new ChromeDriver(options);
    }
}
