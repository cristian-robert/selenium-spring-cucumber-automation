package com.spring.selenium.page.wp.components;

import com.spring.common.annotations.LazyAutowired;
import com.spring.selenium.driver.annotations.PageFragment;
import com.spring.selenium.page.Base;
import com.spring.selenium.page.utils.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

@PageFragment
public class HomePageItemsFragment extends Base {

    @FindBy(className = "ajax_add_to_cart")
    private List<WebElement> addToCartButtons;

    @LazyAutowired
    private Utils utils;
    public void clickRandomAddToCartButton() throws Exception {
        if (addToCartButtons.isEmpty()) {
            throw new Exception("No 'Add To Cart' buttons found");
        }

        int index = new Random().nextInt(addToCartButtons.size());
        WebElement randomButton = addToCartButtons.get(index);

        wait.until(d -> randomButton.isDisplayed());
        utils.scrollAndClickJs(randomButton);
    }
}
