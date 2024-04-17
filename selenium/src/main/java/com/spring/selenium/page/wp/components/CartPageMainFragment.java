package com.spring.selenium.page.wp.components;

import com.spring.common.annotations.LazyAutowired;
import com.spring.selenium.driver.annotations.PageFragment;
import com.spring.selenium.page.Base;
import com.spring.selenium.page.utils.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@PageFragment
public class CartPageMainFragment extends Base {

    @FindBy(css = ".wc-block-cart-items__row")
    private List<WebElement> cartItems;

    @FindBy(css = ".wc-block-components-chip__text")
    private WebElement discountCodeApplied;

    @FindBy(css = ".wc-block-components-totals-coupon-link")
    private WebElement addCouponButton;

    @FindBy(id = "wc-block-components-totals-coupon__input-0")
    private WebElement addCouponInput;

    @FindBy(css = ".wc-block-components-totals-coupon__button")
    private WebElement applyCouponButton;

    @FindBy(css = ".wc-block-cart__submit-button")
    private WebElement proceedToCheckoutButton;

    @FindBy(id = "radio-control-0-free_shipping:4")
    private WebElement freeShippingRadio;

    @FindBy(css = ".wc-block-components-product-metadata__description")
    private List<WebElement> productDescription;

    @LazyAutowired
    private Utils utils;
    public int getCartItemsCount() {
        return utils.waitAndGetElementSize(cartItems);
    }

    public String getDiscountCodeApplied(){
        return utils.waitAndGetElementText(discountCodeApplied);
    }

    public void applyCoupon(String coupon) {
        utils.scrollAndClickJs(addCouponButton);
        utils.waitClearSendKeys(addCouponInput, coupon);
        utils.scrollAndClickJs(applyCouponButton);
    }

    public void proceedToCheckout() {
        utils.isElementDisplayed(proceedToCheckoutButton);
        utils.scrollAndClickJs(proceedToCheckoutButton);
    }

    public void selectFreeShipping(boolean isVirtualProduct) {
        if (!isVirtualProduct) {
            utils.scrollAndClickJs(freeShippingRadio);
        }
    }

    public boolean checkProductDescriptionContainsVirtual(int index) {
        String description = utils.waitAndGetElementText(productDescription.get(index));
        return description.contains("virtual");
    }

    public boolean waitCartPageToLoad() {
        return wait.until(d -> proceedToCheckoutButton.isDisplayed());
    }
}
