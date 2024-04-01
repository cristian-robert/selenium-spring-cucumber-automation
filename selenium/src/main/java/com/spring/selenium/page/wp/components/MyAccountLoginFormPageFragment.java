package com.spring.selenium.page.wp.components;

import com.spring.selenium.driver.annotations.PageFragment;
import com.spring.selenium.page.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PageFragment
public class MyAccountLoginFormPageFragment extends Base {

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(className = "woocommerce-form-login__submit")
    private WebElement loginButton;

    @FindBy(className = "woocommerce-error")
    private WebElement loginError;
    public void login(String username, String password){
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        this.loginButton.click();
    }

    public void waitUserToBeOnMyAccountPage(){
        this.wait.until(d -> d.getCurrentUrl().contains("my-account") && username.isDisplayed());
    }

    public String getLoginError(){
        this.wait.until(d -> loginError.isDisplayed());
        return loginError.getText();
    }
}
