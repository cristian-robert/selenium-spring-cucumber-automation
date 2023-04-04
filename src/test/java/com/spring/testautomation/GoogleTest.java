package com.spring.testautomation;

import com.spring.testautomation.page.google.GooglePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author cristian_iosef
 */

public class GoogleTest extends SpringBaseTestNGTest {

    @Autowired
    private GooglePage googlePage;

    @Test
    public void googleTest(){
        googlePage.goTo();
        Assert.assertTrue(googlePage.isAt());
        googlePage.getSearchComponent().search("Spring");
       Assert.assertTrue(googlePage.getSearchResult().getCount() > 0);
    }
}
