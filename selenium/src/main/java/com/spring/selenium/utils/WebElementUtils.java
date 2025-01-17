package com.spring.selenium.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WebElementUtils {

    @Bean
    public String scrollToWebElement() {
        return "arguments[0].scrollIntoView(true);";
    }

}
