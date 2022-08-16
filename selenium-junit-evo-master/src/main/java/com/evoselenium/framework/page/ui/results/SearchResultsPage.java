package com.evoselenium.framework.page.ui.results;

import com.evoselenium.framework.page.ui.ApplicationHeaderPage;
import com.evoselenium.framework.selenium.TestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SearchResultsPage extends FilterPage {

    private static final By SEARCH_RESULTS_ROOT = By.id("page_main");

     public SearchResultsPage(TestContext context) {
        super(context);
    }

    @Override
    public void verify() {
        assertThat(new ApplicationHeaderPage(getContext()).getActiveMenuItem().contains("search"), is(true));
        waitForPageComponentElementVisible(SEARCH_RESULTS_ROOT);
    }

    WebElement getRootElement() {
        return getElement(SEARCH_RESULTS_ROOT);
    }
}
