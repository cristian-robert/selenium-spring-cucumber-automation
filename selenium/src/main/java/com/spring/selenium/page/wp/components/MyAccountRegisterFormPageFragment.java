package com.spring.selenium.page.wp.components;

import com.spring.common.annotations.LazyAutowired;
import com.spring.selenium.driver.annotations.PageFragment;
import com.spring.selenium.page.Base;
import com.spring.selenium.page.utils.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PageFragment
public class MyAccountRegisterFormPageFragment extends Base {

    @FindBy(id = "reg_email")
    private WebElement emailInput;
    @FindBy(id = "reg_username")
    private WebElement usernameInput;

    @FindBy(id = "reg_password")
    private WebElement passwordInput;

    @FindBy(name = "register")
    private WebElement registerButton;

    @LazyAutowired
    private Utils utils;
    public void fillRegisterForm(String username, String email, String password){
        utils.waitClearSendKeys(usernameInput, username);
        utils.waitClearSendKeys(emailInput, email);
        utils.waitClearSendKeys(passwordInput, password);
    }

    public void clickRegister(){
        utils.scrollAndClickJs(registerButton);
    }
}
