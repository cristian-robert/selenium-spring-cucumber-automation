package com.spring.testautomation.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cristian_iosef
 */
@Configuration
public class WebDriverConfig {

    @Bean
    public WebDriver chromeDriver(){
        WebDriverManager.chromedriver().setup();
        Map prefs = new HashMap();
        prefs.put("profile.default_content_settings.cookies", 2);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.setExperimentalOption("prefs", prefs);

        return new ChromeDriver(options);
    }

    @Bean
    public WebDriverWait webDriverWait(WebDriver driver){
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}
