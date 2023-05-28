package com.spring.testautomation.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cristian_iosef
 */
@Configuration
@Profile("!remote")
public class WebDriverConfig {

    @Bean
    @ConditionalOnProperty(name = "application.browser", havingValue = "chrome")
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
    @ConditionalOnProperty(name = "application.browser", havingValue = "firefox")
    public WebDriver firefoxDriver(){
        WebDriverManager.firefoxdriver().setup();
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("profile.default_content_settings.cookies", 2);
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(firefoxProfile);

        return new FirefoxDriver(options);
    }

    @Bean
    @ConditionalOnMissingBean
    public WebDriver defaultDriver(){
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }


}
