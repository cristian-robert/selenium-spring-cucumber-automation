package com.spring.selenium.driver.config;

import com.github.javafaker.Faker;
import com.spring.selenium.driver.annotations.LazyConfiguration;
import org.springframework.context.annotation.Bean;

@LazyConfiguration
public class FakerConfig {

    @Bean
    public Faker getFaker(){
        return new Faker();
    }

}
