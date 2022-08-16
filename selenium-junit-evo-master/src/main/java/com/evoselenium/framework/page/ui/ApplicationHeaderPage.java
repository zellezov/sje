package com.evoselenium.framework.page.ui;

import com.evoselenium.framework.page.AbstractPageComponent;
import com.evoselenium.framework.page.ui.categories.CategoriesPage;
import com.evoselenium.framework.page.ui.results.FavoritesPage;
import com.evoselenium.framework.selenium.TestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ApplicationHeaderPage extends AbstractPageComponent {

    private static final By APPLICATION_HEADER_ROOT = By.id("main_table");

    private static final By ACTIVE_MENU_ITEM = By.cssSelector(".a_menu_active");

    private static final By LOGO = By.cssSelector(".page_header_logo");

    private static final By SEARCH = By.cssSelector("a[href$='/search/']");

    private static final By FAVORITES = By.cssSelector("a[href$='/favorites/']");

    public ApplicationHeaderPage(TestContext context) {
        super(context);
    }

    @Override
    public void verify() {
        waitForPageComponentElementVisible(APPLICATION_HEADER_ROOT);
    }

    private WebElement getRootElement() {
        return getElement(APPLICATION_HEADER_ROOT);
    }

    public String getActiveMenuItem() {
        return getRootElement().findElement(ACTIVE_MENU_ITEM).getAttribute("href");
    }

    public CategoriesPage clickLogo() {
        click(getRootElement(), LOGO);
        return new CategoriesPage(getContext());
    }

    public SearchPage openSearch() {
        click(getRootElement(), SEARCH);
        return new SearchPage(getContext());
    }

    public FavoritesPage openFavorites() {
        click(getRootElement(), FAVORITES);
        return new FavoritesPage(getContext());
    }
}
