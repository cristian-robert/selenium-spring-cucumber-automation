package com.spring.selenium.page.utils;

import com.spring.selenium.page.Base;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author cristian_iosef
 */
@Component
public class Utils extends Base {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public boolean isAt() {
        return false;
    }

    public void scrollAndClickJs(WebElement element){
        ((JavascriptExecutor) this.applicationContext.getBean(WebDriver.class)).executeScript("arguments[0].scrollIntoView(true);" +
                "arguments[0].click();", element);
    }

    //method that returns all values that are equal to 1
    
}
