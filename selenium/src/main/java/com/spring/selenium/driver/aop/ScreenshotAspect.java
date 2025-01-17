package com.spring.selenium.driver.aop;

import com.spring.selenium.driver.annotations.TakeScreenshot;
import com.spring.selenium.driver.service.ScreenshotService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author cristian_iosef
 */
@Component
@Aspect
public class ScreenshotAspect {

    @Autowired
    private ScreenshotService screenshotService;

    @After("@annotation(takeScreenshot)")
    public void after(TakeScreenshot takeScreenshot){
        this.screenshotService.takeScreenshot();
    }

}
