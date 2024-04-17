package com.spring.selenium.page.wp;

import com.spring.common.annotations.LazyAutowired;
import com.spring.selenium.driver.annotations.Page;
import com.spring.selenium.page.Base;
import com.spring.selenium.page.wp.components.MyAccountLoggedInPageFragment;
import com.spring.selenium.page.wp.components.MyAccountLoginFormPageFragment;
import com.spring.selenium.page.wp.components.MyAccountRegisterFormPageFragment;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Page
public class MyAccountPage extends Base {

    @Value("${application.url}" + "${application.url.path.my-account}")
    private String myAccUrl;

    @Getter
    @LazyAutowired
    private MyAccountLoginFormPageFragment myAccountLoginFormPageFragment;

    @Getter
    @LazyAutowired
    private MyAccountRegisterFormPageFragment myAccountRegisterFormPageFragment;

    @Getter
    @LazyAutowired
    private MyAccountLoggedInPageFragment myAccountLoggedInPageFragment;

    public void goTo(){
        this.driver.get(myAccUrl);
    }



}
