package com.spring.testautomation.page.demoqa.mainPage.components;

import com.spring.testautomation.driver.annotations.PageFragment;
import com.spring.testautomation.enums.ApplicationCategories;
import com.spring.testautomation.enums.ElementsDropdownOptions;
import com.spring.testautomation.page.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;

/**
 * @author cristian_iosef
 */
@PageFragment
public class ElementsSection extends Base {

    @FindBy(xpath = "//div[@class='header-text']")
    private List<WebElement> elementsDropdown;

    @FindBy(xpath = "//div[@class='element-list collapse show']/ul/li")
    private List<WebElement> elementsDropdownList;


    @Override
    public boolean isAt() {
        return this.wait.until((d) -> this.elementsDropdown.size() > 0);
    }

    public void clickSection(String dropdownName){
        this.elementsDropdownList.stream().filter((e) -> e.getText().equals(dropdownName))
                .findFirst()
                .ifPresent(WebElement::click);
    }


    private boolean checkDropdownExpanded(){
        List<String> textFromElements = this.elementsDropdownList.stream()
                .map(WebElement::getText)
                .sorted()
                .toList();

        List<String> textFromEnum = Arrays.stream(ElementsDropdownOptions.values())
                .map(ElementsDropdownOptions::getValue)
                .sorted()
                .toList();

        return textFromElements.equals(textFromEnum);
    }

    public void expandDropdownIfNotExpanded(){
        if(!this.checkDropdownExpanded()){
            this.elementsDropdown.stream()
                    .filter(e -> e.isDisplayed() && e.getText().equals(ApplicationCategories.ELEMENTS.getValue()))
                    .findFirst()
                    .ifPresent(WebElement::click);
        }
    }

}
