package com.spring.testautomation.page.google;

import com.spring.testautomation.page.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.List;

/**

 * @author cristian_iosef
 */
@Component
public class SearchResult extends Base {

    @FindBy(xpath = "//div[@id='search']/div/div/div")
    private List<WebElement> results;

    /**
     * Get count int.
     *
     * @return the int
     */
    public int getCount(){
        return this.results.size();
    }

    @Override
    public boolean isAt() {
        return this.wait.until((d) -> !this.results.isEmpty());
    }
}
