package com.spring.testautomation.enums;

/**
 * @author cristian_iosef
 */
public enum ApplicationCategories {

    ELEMENTS("Elements"),
    FORMS("Forms"),
    ALERTS("Alerts, Frame & Windows"),
    WIDGETS("Widgets"),
    INTERACTIONS("Interactions"),
    BOOK_STORE("Book Store Application");

    private String category;

    public String getValue() {
        return category;
    }

    ApplicationCategories(String category) {
        this.category = category;
    }

    public static String[] getCategories() {
        String[] categories = new String[ApplicationCategories.values().length];
        for (int i = 0; i < ApplicationCategories.values().length; i++) {
            categories[i] = ApplicationCategories.values()[i].category;
        }
        return categories;
    }

}
