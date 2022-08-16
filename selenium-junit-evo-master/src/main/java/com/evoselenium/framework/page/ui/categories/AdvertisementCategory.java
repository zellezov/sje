package com.evoselenium.framework.page.ui.categories;

public enum AdvertisementCategory {

    JOB_AND_BUSINESS("Job and business"),
    TRANSPORT("Transport"),
    REAL_ESTATE("Real estate"),
    CONSTRUCTION("Сonstruction"),
    ELECTRONICS("Electronics"),
    CLOTHES_FOOTWEAR("Clothes, footwear"),
    HOME_STUFF("Home stuff"),
    PRODUCTION_WORK("Production, work"),
    FOR_CHILDREN("For children"),
    ANIMALS("Animals"),
    AGRICULTURE("Agriculture"),
    REST_HOBBIES("Rest, hobbies");

    private String category;

    AdvertisementCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return category;
    }
}
