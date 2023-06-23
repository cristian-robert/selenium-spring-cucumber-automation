package com.spring.testautomation.page.demoqa.mainPage.components;

import com.spring.testautomation.driver.annotations.PageFragment;
import com.spring.testautomation.enums.ApplicationCategories;
import com.spring.testautomation.enums.ElementsDropdownOptions;
import com.spring.testautomation.page.Base;
import com.spring.testautomation.page.utils.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * @author cristian_iosef
 */
@PageFragment
public class MainPageSections extends Base {

    @Autowired
    private Utils utils;
    @FindBy(xpath = "//div[@class='category-cards']/div")
    private List<WebElement> sections;


    @FindBy(xpath = "//div[@class='header-text']")
    private List<WebElement> elementsDropdown;

    @FindBy(xpath = "//div[@class='element-list collapse show']/ul/li")
    private List<WebElement> elementsDropdownList;


    @Override
    public boolean isAt() {
        return this.wait.until((d) -> this.sections.size() > 0);
    }

    public void clickSection(final String sectionName){
        this.sections.stream()
                .filter(e -> e.isDisplayed() && e.isEnabled() && e.getText().equals(sectionName))
                .findFirst()
                .ifPresent(d -> utils.scrollAndClickJs(d));
    }

    public boolean allSectionsMatchText(){
        final List<String> sectionNames = this.sections.stream()
                .map(WebElement::getText)
                .toList();

        final List<String> sectionFromEnum = Arrays.stream(ApplicationCategories.values())
                .map(ApplicationCategories::getValue)
                .toList();

        return sectionNames.equals(sectionFromEnum);
    }

    public void clickDropdownSection(String dropdownName){
        this.elementsDropdownList.stream().filter((e) -> e.getText().equals(dropdownName))
                .findFirst()
                .ifPresent(WebElement::click);
    }


    private boolean checkDropdownExpanded(String dropdown){
        List<String> textFromElements = this.elementsDropdownList.stream()
                .map(WebElement::getText)
                .sorted()
                .toList();

        List<String> textFromEnum = getDropdownOptions(dropdown);
        return textFromElements.equals(textFromEnum);
    }

    public void expandDropdownIfNotExpanded(String dropdown){
        if(!this.checkDropdownExpanded(dropdown)){
            this.elementsDropdown.stream()
                    .filter(e -> e.isDisplayed() && e.getText().equals(dropdown))
                    .findFirst()
                    .ifPresent(WebElement::click);
        }
    }

    public List<String> getDropdownOptions(String dropdown){
        switch(dropdown.toUpperCase()){
            case "ELEMENTS":
                return Arrays.stream(ElementsDropdownOptions.values())
                        .map(ElementsDropdownOptions::getValue)
                        .sorted()
                        .toList();
            default:
                throw new IllegalArgumentException("Invalid dropdown name");
        }
    }

    public void clickOption(String option){
        this.wait.until((d) -> this.elementsDropdownList.stream()
                .anyMatch(e -> e.isDisplayed() && e.getText().equals(option)));
        this.elementsDropdownList.stream()
                .filter(e -> e.isDisplayed() && e.getText().equals(option))
                .findFirst()
                .ifPresent(WebElement::click);
    }
}
