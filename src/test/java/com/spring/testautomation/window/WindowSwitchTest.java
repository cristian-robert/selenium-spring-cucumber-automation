package com.spring.testautomation.window;

import com.spring.testautomation.SpringBaseTestNGTest;
import com.spring.testautomation.driver.service.WindowSwitchService;
import com.spring.testautomation.page.window.MainPage;
import com.spring.testautomation.page.window.PageA;
import com.spring.testautomation.page.window.PageB;
import com.spring.testautomation.page.window.PageC;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author cristian_iosef
 */
public class WindowSwitchTest extends SpringBaseTestNGTest {

    @Autowired
    private MainPage mainPage;

    @Autowired
    private PageA pageA;

    @Autowired
    private PageB pageB;

    @Autowired
    private PageC pageC;

    @Autowired
    private WindowSwitchService windowSwitchService;

    @BeforeClass
    public void setup(){
        this.mainPage.goTo();
        this.mainPage.isAt();
        this.mainPage.launchAllWindows();
    }

    @Test(dataProvider = "data")
    public void switchTest(int index){
        this.pageA.addToArea(index+ "\n");
        this.pageB.addToArea((index * 2) + "\n");
        this.pageC.addToArea((index * 3) + "\n");
    }

    @DataProvider(name = "data")
    public Object[] data(){
        return new Object[]{
                3,
                4,
                1,
                5,
                6,
                2
        };
    }
}
