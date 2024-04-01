package com.spring.selenium.driver.config;

import com.github.javafaker.Faker;
import com.spring.common.annotations.LazyConfiguration;
import com.spring.selenium.driver.annotations.BrowserScopeBean;

@LazyConfiguration
public class FakerConfig {

    @BrowserScopeBean
    public Faker getFaker(){
        return new Faker();
    }

}
