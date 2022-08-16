package com.evoselenium.tests.favorites;

import com.evoselenium.framework.categories.AddToFavorites;
import com.evoselenium.framework.page.ui.ApplicationHeaderPage;
import com.evoselenium.framework.page.ui.categories.AdvertisementCategory;
import com.evoselenium.framework.page.ui.categories.CategoriesPage;
import com.evoselenium.framework.page.ui.results.FilterPage;
import com.evoselenium.framework.selenium.SeleniumTestFramework;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.evoselenium.framework.page.ui.categories.AdvertisementCategory.*;

public class AddToFavoritesFromDifferentCategoriesTest extends SeleniumTestFramework {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddToFavoritesFromDifferentCategoriesTest.class.getName());

    /**
     * Test description:
     * Verify that it is possible to add advertisements from different categories to favorites.
     * <p>
     * Test steps:
     * 1. Add advertisement from 'Construction > Other' category to favorites
     * 2. Add advertisement from 'Agriculture > Other' category to favorites
     * 3. Add advertisement from 'For children > Other' category to favorites
     * 4. Go to 'Favorites' page and verify three advertisements from different categories present
     */
    @Test
    @Category(AddToFavorites.class)
    public void testAddToFavoritesFromDifferentCategories() {

        LOGGER.info("1. Add advertisement from 'Construction > Other' category to favorites");
        final String otherConstruction = addAdvertisementToFavorites(CONSTRUCTION, 3);

        LOGGER.info("2. Add advertisement from 'Agriculture > Other' category to favorites");
        goToCategoriesPage();
        final String otherAgriculture = addAdvertisementToFavorites(AGRICULTURE, 5);

        LOGGER.info("3. Add advertisement from 'For children > Other' category to favorites");
        goToCategoriesPage();
        final String otherForChildren = addAdvertisementToFavorites(FOR_CHILDREN, 7);

        LOGGER.info("4. Go to 'Favorites' page and verify three advertisements from different categories present");
        new ApplicationHeaderPage(getContext()).openFavorites()
                .verifyAdvertisementPresentInCategory(CONSTRUCTION.toString(), otherConstruction)
                .verifyAdvertisementPresentInCategory(AGRICULTURE.toString(), otherAgriculture)
                .verifyAdvertisementPresentInCategory(FOR_CHILDREN.toString(), otherForChildren);
    }

    private String addAdvertisementToFavorites(AdvertisementCategory category, int index) {
        LOGGER.info("Open advertisement category '" + category + " > Other' and get filter result page");
        final FilterPage filterPage = new CategoriesPage(getContext())
                .openSubCategory(category, "Other")
                .andGetFilterPage(getContext());

        LOGGER.info("Capture wording of advertisement in list by index '" + index + "'");
        final String wording = filterPage.getAdvertisementByIndex(index).select().getWording();

        LOGGER.info("Add advertisement to favorites and confirm");
        filterPage.clickAddToFavorites()
                .andGetAlertPopup(getContext())
                .clickOk();

        return wording;
    }

    private void goToCategoriesPage() {
        LOGGER.info("Go to 'Categories' page");
        new ApplicationHeaderPage(getContext()).clickLogo();
    }
}
