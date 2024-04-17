package com.spring.selenium.page.wp.components;

import com.spring.selenium.driver.annotations.PageFragment;
import com.spring.selenium.page.Base;
import com.spring.selenium.page.utils.Utils;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@PageFragment
public class HomePageHeaderFragment extends Base {

    @FindBy(id = "site-header-cart")
    private WebElement cartHeader;

    @FindBy(css = "#site-header-cart .count")
    private WebElement cartCount;

    @FindBy(css = ".remove_from_cart_button")
    private List<WebElement> removeButtons;

    @Autowired
    private Utils utils;

    public void clickCart(){
        this.cartHeader.click();
    }

    public void waitForCartItemCount(int expectedItemCount){
        utils.scrollToElement(cartCount);
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

    public boolean checkNoItemsInCart(){
        return cartCount.getText().equals("0 items");
    }

    public void removeAllItemsFromCart(){
        //hover cartcount element
        if(!checkNoItemsInCart()){
            utils.hoverElement(cartCount);
            //click on remove button
            //wait for cart count to be 0
            //repeat until cart count is 0
            for (WebElement removeButton : removeButtons) {
                utils.scrollAndClickJs(removeButton);
                waitForCartItemCount(0);
            }
        }
    }
}
