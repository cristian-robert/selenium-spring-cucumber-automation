package com.spring.testautomation;

import com.spring.testautomation.driver.annotations.LazyAutowired;
import com.spring.testautomation.page.demoqa.mainPage.DemoQaMainPage;
import org.testng.annotations.Test;

/**
 * @author cristian_iosef
 */
public class TestPurpose extends SpringBaseTestNGTest{

    @LazyAutowired
    private DemoQaMainPage demoQaMainPage;

    @Test
    public void test() throws InterruptedException {
        demoQaMainPage.goToPage("elements");
        demoQaMainPage.getMainPageSections().expandDropdownIfNotExpanded("Elements");
        demoQaMainPage.getMainPageSections().clickSection("Text Box");
//        if(demoQaMainPage.getElementsSection().checkDropdownExpanded()){
//            System.out.println("Dropdown is collapsed");
//        } else {
//            System.out.println("Dropdown is not collapsed");
//            Assert.fail();
//        }
    }
}
