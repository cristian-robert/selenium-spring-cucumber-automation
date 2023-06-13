package com.spring.testautomation.page.google;

import com.spring.testautomation.driver.annotations.PageFragment;
import com.spring.testautomation.page.Base;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @author cristian_iosef
 */
@PageFragment
public class SearchComponent extends Base {

    @FindBy(name = "q")
    private WebElement searchBox;

    @FindBy(xpath = "//button[@id='L2AGLb']/div")
    private WebElement acceptCookiesButton;

    @FindBy(name = "btnK")
    private List<WebElement> searchButton;

    @Override
    public boolean isAt() {
        this.wait.until((d) -> this.searchBox.isDisplayed());
        return true;
    }

    public void acceptCookies(){
        try{
        this.acceptCookiesButton.click();
    } catch (Exception e){
        System.out.println("Cookies already accepted");
    }
    }

    public void search(final String keyword){
        this.searchBox.sendKeys(keyword);
        this.searchBox.sendKeys(Keys.TAB);
        this.searchButton.stream()
                .filter(e -> e.isDisplayed() && e.isEnabled())
                .findFirst()
                .ifPresent(WebElement::click);
    }
}
