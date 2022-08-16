package com.evoselenium.framework.selenium.driverfactory;

import com.evoselenium.framework.selenium.ConfigLoader;
import com.evoselenium.framework.selenium.SeleniumTestFramework;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.remote.UnreachableBrowserException;

import static com.evoselenium.framework.screenshot.TakeScreenshot.takeScreenshot;

public class DriverCommunicationsRule extends TestWatcher {

    private static final String NEVER = "NEVER";

    private static final String ON_ERROR = "ON_ERROR";

    private final SeleniumTestFramework framework;

    public DriverCommunicationsRule(SeleniumTestFramework framework) {
        this.framework = framework;
    }

    private void quitDriver() {
        if (framework.getDriver() != null) {
            try {
                framework.getDriver().quit();
            } catch (SessionNotCreatedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void succeeded(Description description) {
        final String driverStayAlive = ConfigLoader.get().getDriverStayAlive();
        if (NEVER.equals(driverStayAlive) || ON_ERROR.equals(driverStayAlive)) {
            quitDriver();
        }
    }

    @Override
    protected void failed(Throwable throwable, Description description) {
        if (framework.getDriver() != null) {
            try {
                takeScreenshot(description.getMethodName(), framework.getDriver());
            } catch (UnreachableBrowserException e) {
                e.printStackTrace();
            } finally {
                final String driverStayAlive = ConfigLoader.get().getDriverStayAlive();
                if (NEVER.equals(driverStayAlive)) {
                    quitDriver();
                }
            }
        }
    }

}
