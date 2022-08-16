package com.evoselenium.framework.page.ui.categories;

import com.evoselenium.framework.page.AbstractPageComponent;
import com.evoselenium.framework.page.ui.ActionPromise;
import com.evoselenium.framework.selenium.TestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.String.format;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

public class CategoriesPage extends AbstractPageComponent {

    private static final By CATEGORIES_PAGE_ROOT = By.id("page_main_full");

    private static final String CATEGORY = ".main_head2 .a1[title*='%s']";

    private static final String CATEGORY_AND_SUB_CATEGORY = "//a[text()='%s']/../../../../div[last()]/a[text()='%s']";

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoriesPage.class.getName());

    public CategoriesPage(TestContext context) {
        super(context);
    }

    @Override
    public void verify() {
        waitForPageComponentElementVisible(CATEGORIES_PAGE_ROOT);
    }

    private WebElement getRootElement() {
        return getElement(CATEGORIES_PAGE_ROOT);
    }

    public ActionPromise openCategory(AdvertisementCategory advertisementCategory) {
        LOGGER.info("Open advertisementCategory '" + advertisementCategory + "'");
        click(getRootElement(), cssSelector(format(CATEGORY, advertisementCategory.toString())));
        return new ActionPromise(getContext());
    }

    public ActionPromise openSubCategory(AdvertisementCategory advertisementCategory, String subCategory) {
        LOGGER.info("Open sub-advertisementCategory '" + subCategory + "' in advertisementCategory '" + advertisementCategory + "'");
        click(getRootElement(), xpath(format(CATEGORY_AND_SUB_CATEGORY, advertisementCategory.toString(), subCategory)));
        return new ActionPromise(getContext());
    }

}
