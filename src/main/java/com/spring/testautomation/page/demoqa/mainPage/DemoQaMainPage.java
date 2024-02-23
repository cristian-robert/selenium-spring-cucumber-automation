package com.spring.selenium.page.demoqa.mainPage;

import com.spring.selenium.driver.annotations.LazyAutowired;
import com.spring.selenium.driver.annotations.Page;
import com.spring.selenium.page.Base;
import com.spring.selenium.page.demoqa.mainPage.components.CheckboxSection;
import com.spring.selenium.page.demoqa.mainPage.components.ElementsSection;
import com.spring.selenium.page.demoqa.mainPage.components.MainPageSections;
import com.spring.selenium.page.demoqa.mainPage.components.TextBoxSection;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author cristian_iosef
 */
@Page
public class DemoQaMainPage extends Base{

    @LazyAutowired
    private CheckboxSection checkboxSection;

    @LazyAutowired
    private MainPageSections mainPageSections;

    @LazyAutowired
    private ElementsSection elementsSection;

    @LazyAutowired
    private TextBoxSection textBoxSection;

    public TextBoxSection getTextBoxSection() {
        return textBoxSection;
    }

    @Value("${application.url}")
    private String url;

    public MainPageSections getMainPageSections() {
        return mainPageSections;
    }

    public void goTo(){
        this.driver.get(url);

    }

    @Override
    public boolean isAt() {
        return this.mainPageSections.isAt();
    }

    public boolean isOnSelectedPage(String pageName){
        return this.driver.getCurrentUrl().equals(this.url + pageName);
    }

    public void goToPage(String pageName){
        this.driver.get(this.url + pageName);
    }

    public ElementsSection getElementsSection() {
        return elementsSection;
    }

    public CheckboxSection getCheckboxSection() {
        return checkboxSection;
    }
}
