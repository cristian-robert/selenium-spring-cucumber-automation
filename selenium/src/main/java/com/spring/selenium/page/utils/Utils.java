package com.spring.selenium.page.utils;

import com.spring.selenium.page.Base;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author cristian_iosef
 */
@Component
public class Utils extends Base {

    @Autowired
    private ApplicationContext applicationContext;


    public void scrollAndClickJs(WebElement element){
        wait.until(d -> element.isDisplayed() && element.isEnabled());
        ((JavascriptExecutor) this.applicationContext.getBean(WebDriver.class)).executeScript("arguments[0].scrollIntoView(true);" +
                "arguments[0].click();", element);
    }

    public String waitAndGetElementText(WebElement element){
        wait.until(d -> element.isDisplayed());
        return element.getText();
    }

    public int waitAndGetElementSize(List<WebElement> element){
        wait.until(d -> element.get(0).isDisplayed());
        return element.size();
    }
    // wait for element, clear element and send keys to element

    public void waitClearSendKeys(WebElement element, String keys){
        wait.until(d -> element.isDisplayed());
        element.clear();
        element.sendKeys(keys);
    }

    public boolean isElementDisplayed(WebElement element){
        return wait.until(d -> element.isDisplayed() && element.isEnabled());
    }
}
