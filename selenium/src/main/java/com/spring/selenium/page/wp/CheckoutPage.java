package com.spring.selenium.page.wp;

import com.spring.common.annotations.LazyAutowired;
import com.spring.selenium.driver.annotations.PageFragment;
import com.spring.selenium.page.Base;
import com.spring.selenium.page.wp.components.CheckoutPageMainFragment;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@PageFragment
public class CheckoutPage extends Base {

    @Value("${application.url}" + "{application.url.path.checkout}")
    private String checkoutUrl;

    @Getter
    @LazyAutowired
    private CheckoutPageMainFragment checkoutPageMainFragment;

    public void goTo(){
        this.driver.get(checkoutUrl);
    }
}
