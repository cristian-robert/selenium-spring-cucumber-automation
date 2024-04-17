package com.spring.selenium.wpSteps;

import com.spring.common.annotations.LazyAutowired;
import com.spring.selenium.driver.service.ScreenshotService;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

/**
 * @author cristian_iosef
 */
public class CucumberHooks {

    @LazyAutowired
    private ScreenshotService screenshotService;

    @LazyAutowired
    private ApplicationContext applicationContext;


    @Value("${application.url}")
    private String url;

    @Before("@UITest")
    public void beforeScenario(){
        this.applicationContext.getBean(WebDriver.class).manage().deleteAllCookies();
        this.applicationContext.getBean(WebDriver.class).get(url);
    }
    @AfterStep("@UITest")
    public void afterStep(Scenario scenario) {
        if(scenario.isFailed()){
            scenario.attach(this.screenshotService.getScreenshot(), "image/png", scenario.getName());
        }
    }

    @After("@UITest")
    public void afterScenario(){
        this.applicationContext.getBean(WebDriver.class).quit();
    }
}
