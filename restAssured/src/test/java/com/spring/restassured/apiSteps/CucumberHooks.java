package com.spring.restassured.apiSteps;

import com.spring.common.annotations.LazyAutowired;
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
    private ApplicationContext applicationContext;


    @Before()
    public void beforeScenario(){

    }
    @AfterStep()
    public void afterStep(Scenario scenario) {
    }

    @After()
    public void afterScenario(){
    }
}
