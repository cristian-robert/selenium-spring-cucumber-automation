package com.spring.testautomation.googleTest;

import com.spring.testautomation.SpringBaseTestNGTest;
import com.spring.testautomation.driver.annotations.LazyAutowired;
import com.spring.testautomation.driver.service.ScreenshotService;
import com.spring.testautomation.page.google.GooglePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author cristian_iosef
 */

public class GoogleTest extends SpringBaseTestNGTest {

    @LazyAutowired
    private GooglePage googlePage;

    @LazyAutowired
    private ScreenshotService screenshotService;

    @Test
    public void googleTest(){
        googlePage.goTo();
        Assert.assertTrue(googlePage.isAt());
        googlePage.getSearchComponent().search("Spring boot");
        Assert.assertTrue(googlePage.getSearchResult().isAt());
        Assert.assertTrue(googlePage.getSearchResult().getCount() > 0);
        screenshotService.takeScreenshot();
    }
}
