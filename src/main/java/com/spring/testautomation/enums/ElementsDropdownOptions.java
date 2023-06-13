package com.spring.testautomation.enums;

/**
 * @author cristian_iosef
 */
public enum ElementsDropdownOptions {

    TEXT_BOX("Text Box"),
    CHECK_BOX("Check Box"),
    RADIO_BUTTON("Radio Button"),
    WEB_TABLES("Web Tables"),
    BUTTONS("Buttons"),
    LINKS("Links"),
    BROKEN_LINKS_IMAGES("Broken Links - Images"),
    UPLOAD_DOWNLOAD("Upload and Download"),
    DYNAMIC_PROPERTIES("Dynamic Properties");

    private String option;

    public String getValue() {
        return option;
    }

    ElementsDropdownOptions(String option) {
        this.option = option;
    }
}
