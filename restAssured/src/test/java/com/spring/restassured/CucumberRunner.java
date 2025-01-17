package com.spring.restassured;

import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.DataProvider;

/**
 * @author cristian_iosef
 */

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
@CucumberOptions(
        features = "classpath:features",
        glue ={"com.spring.restassured.apiSteps"},
        plugin = {"pretty", "html:target/cucumber-report/cucumber-pretty.html", "json:target/cucumber-report/cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"})
public class CucumberRunner extends AbstractTestNGCucumberTests {
    @DataProvider(parallel = true)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
