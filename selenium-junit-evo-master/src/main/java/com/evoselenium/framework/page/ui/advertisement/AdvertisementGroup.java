package com.evoselenium.framework.page.ui.advertisement;

import com.evoselenium.framework.selenium.TestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AdvertisementGroup {

    private static final By CATEGORY = By.cssSelector(".msg_column");

    private static final By FILTER_RESULT = By.cssSelector("tr:not(:first-child)");

    private WebElement rootElement;

    private TestContext context;

    public AdvertisementGroup(WebElement rootElement, TestContext context) {
        this.rootElement = rootElement;
        this.context = context;
    }

    public String getCategory() {
        return rootElement.findElement(CATEGORY).getText();
    }


    private List<AdvertisementRow> getAdvertisements() {
        return rootElement.findElements(FILTER_RESULT)
                .stream()
                .map(result -> new AdvertisementRow(result, context))
                .collect(Collectors.toList());
    }

    public boolean isAdvertisementPresent(String wording) {
        return getAdvertisements().stream()
                .anyMatch(advertisementRow -> advertisementRow.getWording().contains(wording));
    }

    public AdvertisementGroup verifyAdvertisementPresentByWording(String wording) {
        assertThat("Unable to find advertisement by wording: " + wording, isAdvertisementPresent(wording), is(true));
        return this;
    }

}
