package com.evoselenium.framework.page.ui.advertisement;

import com.evoselenium.framework.page.AbstractPageComponent;
import com.evoselenium.framework.page.ui.ActionPromise;
import com.evoselenium.framework.selenium.TestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AdvertisementPage extends AbstractPageComponent {

    private static final By ADVERTISEMENT_PAGE_ROOT = By.id("content_main_div");

    private static final By ADD_TO_FAVORITES = By.xpath("../../../..//a[@id='a_fav']");

    public AdvertisementPage(TestContext context) {
        super(context);
    }

    @Override
    public void verify() {
        waitForPageComponentElementVisible(ADVERTISEMENT_PAGE_ROOT);
    }

    private WebElement getRootElement() {
        return getElement(ADVERTISEMENT_PAGE_ROOT);
    }

    public ActionPromise clickAddToFavorites() {
        click(getRootElement(), ADD_TO_FAVORITES);
        return new ActionPromise(getContext());
    }

}
