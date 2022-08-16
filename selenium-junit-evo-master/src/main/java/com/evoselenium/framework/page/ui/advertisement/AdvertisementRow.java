package com.evoselenium.framework.page.ui.advertisement;

import com.evoselenium.framework.selenium.TestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdvertisementRow {

    private static final By SELECT = By.cssSelector(".msga2.pp0 input");

    private static final By WORDING = By.cssSelector(".msg2 .d1 .am");

    private static final Logger LOGGER = LoggerFactory.getLogger(AdvertisementRow.class.getName());

    private WebElement rootElement;

    private TestContext context;

    public AdvertisementRow(WebElement rootElement, TestContext context) {
        this.rootElement = rootElement;
        this.context = context;
    }

    public AdvertisementRow select() {
        LOGGER.info("Select advertisement");
        WebElement checkbox = rootElement.findElement(SELECT);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        return this;
    }

    public String getWording() {
        String wording = rootElement.findElement(WORDING)
                .getText();
        if (wording.length() > 50) {
            wording = wording.substring(0, 50);
        }
        return wording;


    }

    public AdvertisementPage openDetails() {
        LOGGER.info("Open advertisement details");
        rootElement.findElement(WORDING).click();
        return new AdvertisementPage(context);
    }

}
