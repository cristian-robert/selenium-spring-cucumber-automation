package com.spring.selenium.driver.config;

import com.spring.selenium.driver.annotations.BrowserScopeBean;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActionsConfig {

    //create bean for Actions class

    @BrowserScopeBean
    public Actions actions(WebDriver driver){
        return new Actions(driver);
    }
}
