package com.spring.testautomation.driver.aop;

import com.spring.testautomation.driver.annotations.TakeScreenshot;
import com.spring.testautomation.driver.service.ScreenshotService;
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
