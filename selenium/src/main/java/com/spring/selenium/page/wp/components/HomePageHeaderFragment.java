package com.spring.selenium.page.wp.components;

import com.spring.selenium.driver.annotations.PageFragment;
import com.spring.selenium.page.Base;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PageFragment
public class HomePageHeaderFragment extends Base {

    @FindBy(id = "site-header-cart")
    private WebElement cartHeader;

    @FindBy(css = "#site-header-cart .count")
    private WebElement cartCount;

    public void clickCart(){
        this.cartHeader.click();
    }

    public void waitForCartItemCount(int expectedItemCount){
        try {
            wait.until(driver -> {
                String expectedText = (expectedItemCount == 1) ? "1 item" : expectedItemCount + " items";
                String actualHeaderText = cartCount.getText();
                return actualHeaderText.equals(expectedText);
            });
        } catch (TimeoutException e) {
            throw new RuntimeException("Expected cart header count to be according to the number of items after 5s", e);
        }
    }
}
