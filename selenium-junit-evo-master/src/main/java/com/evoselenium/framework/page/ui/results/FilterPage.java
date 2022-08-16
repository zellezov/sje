package com.evoselenium.framework.page.ui.results;

import com.evoselenium.framework.page.AbstractPageComponent;
import com.evoselenium.framework.page.ui.ActionPromise;
import com.evoselenium.framework.page.ui.advertisement.AdvertisementRow;
import com.evoselenium.framework.selenium.TestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class FilterPage extends AbstractPageComponent {

    protected static final By FILTER_PAGE_ROOT = By.id("filter_frm");

    protected static final By FILTER_RESULTS = By.cssSelector("table[align='center']");

    private static final By FILTER_RESULT = By.cssSelector("tr:not(:first-child)");

    private static final By ADD_TO_FAVORITES = By.id("a_fav_sel");

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterPage.class.getName());

    public FilterPage(TestContext context) {
        super(context);
    }

    @Override
    public void verify() {
        waitForPageComponentElementVisible(FILTER_PAGE_ROOT);
    }

    WebElement getRootElement() {
        return getElement(FILTER_PAGE_ROOT);
    }

    private List<AdvertisementRow> getFilterResults() {
        return getRootElement().findElement(FILTER_RESULTS)
                .findElements(FILTER_RESULT)
                .stream()
                .map(result -> new AdvertisementRow(result, getContext()))
                .collect(Collectors.toList());
    }

    public AdvertisementRow getAdvertisementByIndex(int index) {
        LOGGER.info("Get advertisement by index '" + index + "'");
        return getFilterResults().get(index);
    }

    public String selectAdvertisementByIndex(int index) {
        AdvertisementRow advertisement = getAdvertisementByIndex(index);
        advertisement.select();
        return advertisement.getWording();
    }

    public ActionPromise selectAdvertisementByWording(String wording) {
        getFilterResults().stream()
                .filter(advertisement -> advertisement.getWording().contains(wording))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Unable to find advertisement by wording: '" + wording + "'"))
                .select();
        return new ActionPromise(getContext());
    }

    public ActionPromise clickAddToFavorites() {
        click(getRootElement(), ADD_TO_FAVORITES);
        return new ActionPromise(getContext());
    }

    public String addAdvertisementToFavoritesByIndex(int index) {
        String wording = getAdvertisementByIndex(index).select().getWording();
        clickAddToFavorites().andGetAlertPopup(getContext())
                .clickOk();
        return wording;
    }
}
