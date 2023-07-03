package com.spring.testautomation.driver.config;

import com.spring.testautomation.driver.annotations.LazyConfiguration;
import com.spring.testautomation.driver.annotations.ThreadScopeBean;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.*;

import java.net.URL;

/**
 * @author cristian_iosef
 */
@LazyConfiguration
@Profile("remote")
public class RemoteWebDriverConfig {

    @Value("${selenium.grid.url}")
    private URL url;


    @Value("${default.timeout:30}")
    private int timeout;

    @ThreadScopeBean
    @ConditionalOnProperty(name = "application.browser", havingValue = "firefox")
    public WebDriver remoteFirefoxDriver(){

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        firefoxOptions.setProfile(profile);
        return new RemoteWebDriver(this.url, firefoxOptions, false);
    }

    @ThreadScopeBean
    @ConditionalOnProperty(name = "application.browser", havingValue = "chrome")
    public WebDriver remoteChromeDriver(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--window-size=1920x1080");
        return new RemoteWebDriver(this.url, options, false);
    }

}
