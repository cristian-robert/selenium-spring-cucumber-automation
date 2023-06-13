package com.spring.testautomation.driver.config;

import com.spring.testautomation.driver.annotations.LazyConfiguration;
import com.spring.testautomation.driver.annotations.ThreadScopeBean;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cristian_iosef
 */
@LazyConfiguration
@Profile("!remote")
public class WebDriverConfig {

    @ThreadScopeBean
    @Primary
//    @ConditionalOnProperty(name = "application.browser", havingValue = "chrome")
    public WebDriver chromeDriver(){
        WebDriverManager.chromedriver().setup();
        Map prefs = new HashMap();
        prefs.put("profile.default_content_settings.cookies", 2);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless");
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--start-maximized");
        return new ChromeDriver(options);
    }

    @ThreadScopeBean
//    @ConditionalOnProperty(name = "application.browser", havingValue = "firefox")
    public WebDriver firefoxDriver(){
        WebDriverManager.firefoxdriver().setup();
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("profile.default_content_settings.cookies", 2);
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(firefoxProfile);

        return new FirefoxDriver(options);
    }

    @ThreadScopeBean
//    @ConditionalOnMissingBean
    public WebDriver defaultDriver(){
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }


}
