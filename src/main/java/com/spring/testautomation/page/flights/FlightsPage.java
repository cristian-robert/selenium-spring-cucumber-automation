package com.spring.testautomation.page.flights;

import com.spring.testautomation.driver.annotations.Page;
import com.spring.testautomation.driver.annotations.TakeScreenshot;
import com.spring.testautomation.page.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cristian_iosef
 */
@Page
public class FlightsPage extends Base {

    @FindBy(xpath = "//div[@jscontroller='xK5aDe']")
    private List<WebElement> flightsButtons;

    public void goTo(final String url) {
        this.driver.get(url);
    }

    @TakeScreenshot
    public List<String> getLabels() {
        return this.flightsButtons
                .stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAt() {
        return this.wait.until((d) -> !this.flightsButtons.isEmpty());
    }
}
