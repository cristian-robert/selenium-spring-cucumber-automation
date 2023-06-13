package com.spring.testautomation.driver.aop;

import com.spring.testautomation.driver.annotations.Window;
import com.spring.testautomation.driver.service.WindowSwitchService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author cristian_iosef
 */
@Aspect
@Component
public class WindowAspect {

    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private WindowSwitchService windowSwitchService;

    @Before("@target(window) && within(com.spring.testautomation..*)")
    public void before(Window window){
        this.windowSwitchService.switchByTitle(window.value());
    }

    @After("@target(window) && within(com.spring.testautomation..*)")
    public void after(Window window){
        this.windowSwitchService.switchByIndex(0);
    }
}
