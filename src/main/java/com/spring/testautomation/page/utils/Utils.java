package com.spring.testautomation.page.utils;

import com.spring.testautomation.page.Base;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author cristian_iosef
 */
@Component
public class Utils extends Base {

    @Autowired
    private WebDriver driver;

    @Override
    public boolean isAt() {
        return false;
    }

    public void scrollAndClickJs(WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);" +
                "arguments[0].click();", element);
    }

    //method that returns all values that are equal to 1
    
}
