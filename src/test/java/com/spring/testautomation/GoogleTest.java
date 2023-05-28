package com.spring.testautomation;

import com.spring.testautomation.page.google.GooglePage;
import com.spring.testautomation.util.ScreenShotUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author cristian_iosef
 */

public class GoogleTest extends SpringBaseTestNGTest {

    @Autowired
    private GooglePage googlePage;

    @Lazy
    @Autowired
    private ScreenShotUtil screenShotUtil;

    @Test
    public void googleTest(){
        googlePage.goTo();
        Assert.assertTrue(googlePage.isAt());
        googlePage.getSearchComponent().search("Spring");
        Assert.assertTrue(googlePage.getSearchResult().isAt());
       Assert.assertTrue(googlePage.getSearchResult().getCount() > 0);
        screenShotUtil.takeScreenShot();
    }
}
