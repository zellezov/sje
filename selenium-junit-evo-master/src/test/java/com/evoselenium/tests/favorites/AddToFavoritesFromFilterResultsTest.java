package com.evoselenium.tests.favorites;

import com.evoselenium.framework.categories.AddToFavorites;
import com.evoselenium.framework.page.ui.advertisement.AdvertisementRow;
import com.evoselenium.framework.page.ui.categories.CategoriesPage;
import com.evoselenium.framework.selenium.SeleniumTestFramework;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.evoselenium.framework.page.ui.categories.AdvertisementCategory.ANIMALS;

public class AddToFavoritesFromFilterResultsTest extends SeleniumTestFramework {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddToFavoritesFromFilterResultsTest.class.getName());

    private static final String CATS_KITTENS = "Cats, kittens";

    /**
     * Test description:
     * Verify that it is possible to add advertisement to favorites from filter results page.
     * <p>
     * Test steps:
     * 1. Open advertisement category 'Animals > Cats, kittens' and get third advertisement
     * 2. Capture advertisement wording
     * 3. Add advertisement to favorites, go to 'Favorites' page and verify it is present
     */
    @Test
    @Category(AddToFavorites.class)
    public void testAddToFavoritesFromFilterResults() {

        LOGGER.info("1. Open advertisement category 'Animals > Cats, kittens' and get third advertisement");
        AdvertisementRow advertisement = new CategoriesPage(getContext())
                .openSubCategory(ANIMALS, CATS_KITTENS)
                .andGetFilterPage(getContext())
                .getAdvertisementByIndex(2);

        LOGGER.info("2. Capture advertisement wording");
        String catsWording = advertisement.getWording();

        LOGGER.info("3. Add advertisement to favorites, go to 'Favorites' page and verify it is present");
        advertisement.openDetails()
                .clickAddToFavorites()
                .andGetAlertPopup(getContext())
                .clickOk()
                .andGetApplicationHeader(getContext())
                .openFavorites()
                .verifyAdvertisementPresentInCategory(CATS_KITTENS, catsWording);
    }
}
