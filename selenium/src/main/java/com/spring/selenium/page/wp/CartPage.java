package com.spring.selenium.page.wp;

import com.spring.common.annotations.LazyAutowired;
import com.spring.selenium.driver.annotations.Page;
import com.spring.selenium.page.Base;
import com.spring.selenium.page.wp.components.CartPageMainFragment;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Page
public class CartPage extends Base {

    @Value("${application.url}" + "{application.url.path.cart}")
    private String cartUrl;

    @Getter
    @LazyAutowired
    private CartPageMainFragment cartPageMainFragment;

    public void goTo(){
        this.driver.get(cartUrl);
    }
}
