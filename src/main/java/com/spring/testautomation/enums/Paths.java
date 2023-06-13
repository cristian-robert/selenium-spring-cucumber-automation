package com.spring.testautomation.enums;

/**
 * @author cristian_iosef
 */
public enum Paths {

    ELEMENTS("elements"),
    FORMS("forms"),
    ALERTS("alertsWindows"),
    WIDGETS("widgets"),
    INTERACTIONS("interaction"),
    BOOK_STORE("books");

    private String path;

    public String getValue() {
        return path;
    }

    Paths(String path) {
        this.path = path;
    }

}
