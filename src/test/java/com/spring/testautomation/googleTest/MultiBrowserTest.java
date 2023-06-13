package com.spring.testautomation.googleTest;

import com.spring.testautomation.SpringBaseTestNGTest;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.Test;

/**
 * @author cristian_iosef
 */
public class MultiBrowserTest extends SpringBaseTestNGTest {

    @Autowired
    private ApplicationContext ctx;

    @Test
    public void browserTest(){
        this.ctx.getBean("chromeDriver", ChromeDriver.class).get("http://www.google.com");
        this.ctx.getBean("firefoxDriver", FirefoxDriver.class).get("http://www.google.com");
    }
}
