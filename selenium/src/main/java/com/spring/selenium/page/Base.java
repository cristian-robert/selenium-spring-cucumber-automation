package com.spring.selenium.page;

import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * @author cristian_iosef
 */
public abstract class Base {

    @Autowired
    protected WebDriver driver;

    @Autowired
    public WebDriverWait wait;

    @Autowired
    private Faker fakerConfig;

    @Autowired
    private ApplicationContext context;

    protected WebDriver getBeanDriver(){
        return context.getBean(WebDriver.class);
    }
    @PostConstruct
    private void init(){
        PageFactory.initElements(this.driver, this);
    }


    @PreDestroy
    public void close(){
        this.driver.quit();
    }


}
