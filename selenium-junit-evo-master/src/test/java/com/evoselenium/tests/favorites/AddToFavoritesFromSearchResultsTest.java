package com.evoselenium.tests.favorites;

import com.evoselenium.framework.categories.AddToFavorites;
import com.evoselenium.framework.page.ui.ApplicationHeaderPage;
import com.evoselenium.framework.selenium.SeleniumTestFramework;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddToFavoritesFromSearchResultsTest extends SeleniumTestFramework {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddToFavoritesFromSearchResultsTest.class.getName());

    /**
     * Test description:
     * Verify that it is possible to add advertisement to favorites from search results page.
     * <p>
     * Test steps:
     * 1. Go to 'Search' page and search for criteria 'Cessna', add third advertisement to favorites
     * 2. Go to 'Favorites' page and verify advertisement is present
     */
    @Test
    @Category(AddToFavorites.class)
    public void testAddToFavoritesFromSearchResults() {

        LOGGER.info("1. Go to 'Search' page and search for criteria 'Cessna', add third advertisement to favorites");
        String wording = new ApplicationHeaderPage(getContext()).openSearch()
                .searchForAdvertisementByCriteria("Cessna")
                .addAdvertisementToFavoritesByIndex(3);

        LOGGER.info("2. Go to 'Favorites' page and verify advertisement is present");
        new ApplicationHeaderPage(getContext()).openFavorites()
                .verifyAdvertisementPresentByWording(wording);
    }
}
