package com.spring.selenium.page.window;

import com.spring.selenium.driver.annotations.Window;
import com.spring.selenium.page.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author cristian_iosef
 */
@Window("Page A")
public class PageA extends Base {

    @FindBy(id = "area")
    private WebElement textArea;

    public void addToArea(final String text){
        this.textArea.sendKeys(text);
    }

    @Override
    public boolean isAt() {
        return this.wait.until((d) -> this.textArea.isDisplayed());
    }
}
