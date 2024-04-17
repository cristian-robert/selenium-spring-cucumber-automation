package com.spring.selenium.page.wp.components;

import com.spring.selenium.driver.annotations.PageFragment;
import com.spring.selenium.page.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@PageFragment
public class MyAccountLoggedInPageFragment extends Base {

    @FindBy(className = "woocommerce-MyAccount-navigation-link--customer-logout")
    private WebElement logoutButton;

    @FindBy(css = ".woocommerce-MyAccount-content p")
    private List<WebElement> welcomeMessages;

    public void logout(){
        this.logoutButton.click();
    }

    public boolean waitUserToBeLoggedIn(){
        return this.wait.until(d -> logoutButton.isDisplayed());
    }

    public String getWelcomeMessage(){
        return welcomeMessages.get(0).getText();
    }
}
