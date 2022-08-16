package com.evoselenium.framework.page.ui;

import com.evoselenium.framework.page.AbstractPageComponent;
import com.evoselenium.framework.page.ui.results.SearchResultsPage;
import com.evoselenium.framework.selenium.TestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPage extends AbstractPageComponent {

    private static final By SEARCH_PAGE_ROOT = By.cssSelector("form[action$='/search-result/']");

    private static final By SEARCH_INPUT = By.id("ptxt");

    private static final By SUGGESTIONS = By.cssSelector("#preload_txt div");

    private static final By SEARCH_BUTTON = By.id("sbtn");

    public SearchPage(TestContext context) {
        super(context);
    }

    @Override
    public void verify() {
        waitForPageComponentElementVisible(SEARCH_PAGE_ROOT);
    }

    private WebElement getRootElement() {
        return getElement(SEARCH_PAGE_ROOT);
    }

    private SearchPage setSearchCriteria(String criteria) {
        getRootElement().findElement(SEARCH_INPUT).sendKeys(criteria);
        getContext().getWait().until(ExpectedConditions.visibilityOfElementLocated(SUGGESTIONS));
        return this;
    }

    private SearchPage selectSuggestedSearchCriteria(String criteria) {
        WebElement searchSuggestion = getRootElement().findElements(SUGGESTIONS)
                .stream()
                .filter(suggestion -> suggestion.getText().contentEquals(criteria))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Unable to find suggestion by criteria: '" + criteria + "'"));
        click(searchSuggestion);
        return this;
    }

    private SearchResultsPage clickSearchButton() {
        click(getRootElement(), SEARCH_BUTTON);
        return new SearchResultsPage(getContext());
    }

    public SearchResultsPage searchForAdvertisementByCriteria(String criteria) {
        return setSearchCriteria(criteria)
                .selectSuggestedSearchCriteria(criteria)
                .clickSearchButton();
    }
}
