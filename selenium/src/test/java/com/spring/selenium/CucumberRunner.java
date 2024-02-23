package com.spring.selenium;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * @author cristian_iosef
 */
@CucumberOptions(
        features = "classpath:features",
        glue ={"com.spring.selenium.demoqa"},
        plugin = {"pretty", "html:target/cucumber-report/cucumber-pretty.html", "json:target/cucumber-report/cucumber.json"})
public class CucumberRunner extends AbstractTestNGCucumberTests {
    @DataProvider(parallel = true)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
