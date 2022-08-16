package com.evoselenium.framework.page.ui.results;

import com.evoselenium.framework.page.ui.ApplicationHeaderPage;
import com.evoselenium.framework.page.ui.advertisement.AdvertisementGroup;
import com.evoselenium.framework.selenium.TestContext;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FavoritesPage extends FilterPage {

    public FavoritesPage(TestContext context) {
        super(context);
    }

    @Override
    public void verify() {
        assertThat(new ApplicationHeaderPage(getContext()).getActiveMenuItem().contains("favorites"), is(true));
        waitForPageComponentElementVisible(FILTER_PAGE_ROOT);
    }

    private List<AdvertisementGroup> getAdvertisementGroups() {
        return getRootElement().findElements(FILTER_RESULTS)
                .stream()
                .map(element -> new AdvertisementGroup(element, getContext()))
                .collect(Collectors.toList());
    }

    public AdvertisementGroup getAdvertisementGroupByCategory(String category) {
        return getAdvertisementGroups().stream()
                .filter(group -> group.getCategory().contains(category))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Unable to find group by category: '" + category + "'"));
    }

    public FavoritesPage verifyAdvertisementPresentByWording(String wording) {
        assertThat("Unable to find advertisement in any category by wording: '" + wording + "'",
                getAdvertisementGroups().stream()
                        .anyMatch(advertisementGroup -> advertisementGroup.isAdvertisementPresent(wording)),
                is(true));
        return this;
    }

    public FavoritesPage verifyAdvertisementPresentInCategory(String category, String wording) {
        getAdvertisementGroupByCategory(category).verifyAdvertisementPresentByWording(wording);
        return this;
    }
}
