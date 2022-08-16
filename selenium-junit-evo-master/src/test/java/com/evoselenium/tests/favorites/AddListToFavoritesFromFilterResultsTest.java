package com.evoselenium.tests.favorites;

import com.evoselenium.framework.categories.AddToFavorites;
import com.evoselenium.framework.page.ui.categories.CategoriesPage;
import com.evoselenium.framework.page.ui.results.FilterPage;
import com.evoselenium.framework.selenium.SeleniumTestFramework;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.evoselenium.framework.page.ui.categories.AdvertisementCategory.REST_HOBBIES;

public class AddListToFavoritesFromFilterResultsTest extends SeleniumTestFramework {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddListToFavoritesFromFilterResultsTest.class.getName());

    private static final String EL_GUITARS = "El. guitars";

    /**
     * Test description:
     * Verify that it is possible to add list of advertisements to favorites from advertisement list page.
     * <p>
     * Test steps:
     * 1. Open advertisement category 'Entertainment > Music, instrument, education > El. guitars'
     * 2. Capture wording of first, third and fifth advertisement in list and select them
     * 3. Add advertisements to favorites and verify they have been successfully added
     */
    @Test
    @Category(AddToFavorites.class)
    public void testAddListToFavoritesFromFilterResults() {

        LOGGER.info("1. Open advertisement category 'Entertainment > Music, instrument, education > El. guitars'");
        final FilterPage entertainment = new CategoriesPage(getContext())
                .openSubCategory(REST_HOBBIES, "Music, instrument, education")
                .andGetCategoryPage(getContext())
                .openSubCategory(EL_GUITARS)
                .andGetFilterPage(getContext());

        LOGGER.info("2. Capture wording of first, third and fifth advertisement in list and select them");
        final String guitarsWordingFirst = entertainment.selectAdvertisementByIndex(0);
        final String guitarsWordingThird = entertainment.selectAdvertisementByIndex(2);
        final String guitarsWordingFifth = entertainment.selectAdvertisementByIndex(4);

        LOGGER.info("3. Add advertisements to favorites and verify they have been successfully added");
        entertainment.clickAddToFavorites()
                .andGetAlertPopup(getContext())
                .clickOk()
                .andGetApplicationHeader(getContext())
                .openFavorites()
                .getAdvertisementGroupByCategory(EL_GUITARS)
                .verifyAdvertisementPresentByWording(guitarsWordingFirst)
                .verifyAdvertisementPresentByWording(guitarsWordingThird)
                .verifyAdvertisementPresentByWording(guitarsWordingFifth);
    }
}
