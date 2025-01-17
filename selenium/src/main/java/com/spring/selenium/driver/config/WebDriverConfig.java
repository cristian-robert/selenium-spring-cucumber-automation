package com.spring.selenium.driver.config;

import com.spring.common.annotations.LazyConfiguration;
import com.spring.selenium.driver.annotations.BrowserScopeBean;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;

import java.util.HashMap;
import java.util.Map;

@LazyConfiguration
@Profile("!remote")
public class WebDriverConfig {
    private static final String BROWSER_PROPERTY = "application.browser";
    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";

    @BrowserScopeBean
    @ConditionalOnProperty(name = BROWSER_PROPERTY, havingValue = CHROME)
    public WebDriver chromeDriver() {
        ChromeOptions options = getChromeOptions();
        return new ChromeDriver(options);
    }

    @BrowserScopeBean
    @ConditionalOnProperty(name = BROWSER_PROPERTY, havingValue = FIREFOX)
    public WebDriver firefoxDriver() {
        FirefoxOptions options = getFirefoxOptions();
        return new FirefoxDriver(options);
    }

    private ChromeOptions getChromeOptions() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_settings.cookies", 2);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage", "--remote-allow-origins=*", "--window-size=1920,1080", "--start-maximized");
        options.setExperimentalOption("prefs", prefs);
        return options;
    }

    private FirefoxOptions getFirefoxOptions() {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("profile.default_content_settings.cookies", 2);

        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(firefoxProfile);
        WebDriverManager.firefoxdriver().setup();
        return options;
    }
}
