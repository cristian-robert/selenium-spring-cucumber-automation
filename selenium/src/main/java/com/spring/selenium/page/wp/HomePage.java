package com.spring.selenium.page.wp;

import com.spring.common.annotations.LazyAutowired;
import com.spring.selenium.driver.annotations.Page;
import com.spring.selenium.page.Base;
import com.spring.selenium.page.wp.components.HomePageHeaderFragment;
import com.spring.selenium.page.wp.components.HomePageItemsFragment;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Page
public class HomePage extends Base {

    @Value("${application.url}")
    private String homeUrl;

    @Getter
    @LazyAutowired
    private HomePageItemsFragment homePageItemsFragment;

    @Getter
    @LazyAutowired
    private HomePageHeaderFragment homePageHeaderFragment;

    public void goTo(){
        this.driver.get(homeUrl);
    }
}
