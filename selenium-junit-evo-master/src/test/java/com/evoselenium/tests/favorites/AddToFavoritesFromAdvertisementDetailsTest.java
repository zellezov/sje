package com.evoselenium.tests.favorites;

import com.evoselenium.framework.categories.AddToFavorites;
import com.evoselenium.framework.page.ui.advertisement.AdvertisementRow;
import com.evoselenium.framework.page.ui.categories.CategoriesPage;
import com.evoselenium.framework.selenium.SeleniumTestFramework;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.evoselenium.framework.page.ui.categories.AdvertisementCategory.TRANSPORT;

public class AddToFavoritesFromAdvertisementDetailsTest extends SeleniumTestFramework {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddToFavoritesFromAdvertisementDetailsTest.class.getName());

    /**
     * Test description:
     * Verify that it is possible to add advertisement to favorites from advertisement details page.
     * <p>
     * Test steps:
     * 1. Open advertisement category 'Transport > Cars > Volvo'
     * 2. Capture wording of first advertisement in list
     * 3. Add advertisement to favorites, go to 'Favorites' page and verify it is present
     */
    @Test
    @Category(AddToFavorites.class)
    public void testAddToFavoritesFromAdvertisementDetails() {

        LOGGER.info("1. Open advertisement category 'Transport > Cars > Volvo'");
        AdvertisementRow advertisement = new CategoriesPage(getContext())
                .openCategory(TRANSPORT)
                .andGetCategoryPage(getContext())
                .openSubCategory("Cars")
                .andGetCategoryPage(getContext())
                .openSubCategory("Volvo")
                .andGetFilterPage(getContext())
                .getAdvertisementByIndex(0);

        LOGGER.info("2. Capture wording of first advertisement in list");
        String wording = advertisement.getWording();

        LOGGER.info("3. Add advertisement to favorites, go to 'Favorites' page and verify it is present");
        advertisement.openDetails()
                .clickAddToFavorites()
                .andGetAlertPopup(getContext())
                .clickOk()
                .andGetApplicationHeader(getContext())
                .openFavorites()
                .getAdvertisementGroupByCategory("Volvo")
                .verifyAdvertisementPresentByWording(wording);
    }
}
