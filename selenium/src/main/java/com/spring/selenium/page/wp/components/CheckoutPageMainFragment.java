package com.spring.selenium.page.wp.components;

import com.spring.common.annotations.LazyAutowired;
import com.spring.selenium.driver.annotations.PageFragment;
import com.spring.selenium.page.Base;
import com.spring.selenium.page.utils.Utils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@PageFragment
public class CheckoutPageMainFragment extends Base {

    @FindBy(id = "email")
    private WebElement contactInformationEmailAddress;

    @FindBy(id = "billing-first_name")
    private WebElement billingAddressFirstName;

    @FindBy(id = "billing-last_name")
    private WebElement billingAddressLastName;

    @FindBy(id = "billing-company")
    private WebElement billingAddressCompany;

    @FindBy(id = "billing-address_1")
    private WebElement billingAddressAddress1;

    @FindBy(id = "billing-address_2")
    private WebElement billingAddressAddress2;

    @FindBy(id = "billing-city")
    private WebElement billingAddressCity;

    @FindBy(id = "billing-state")
    private WebElement billingAddressState;

    @FindBy(id = "billing-postcode")
    private WebElement billingAddressPostcode;

    @FindBy(id = "billing-phone")
    private WebElement billingAddressPhone;

    @FindBy(id = "createaccount")
    private WebElement createAccountCheckbox;

    @FindBy(id = "account_password")
    private WebElement createAccountPassword;

    @FindBy(css = ".wc-block-checkout__add-note .wc-block-components-checkbox__input")
    private WebElement orderNotesCheckbox;

    @FindBy(id = "shipping-first_name")
    private WebElement shippingAddressFirstName;

    @FindBy(id = "shipping-last_name")
    private WebElement shippingAddressLastName;

    @FindBy(id = "shipping-company")
    private WebElement shippingAddressCompany;

    @FindBy(id = "shipping-country")
    private WebElement shippingAddressCountry;

    @FindBy(id = "shipping-address_1")
    private WebElement shippingAddressAddress1;

    @FindBy(id = "shipping-address_2")
    private WebElement shippingAddressAddress2;

    @FindBy(id = "shipping-city")
    private WebElement shippingAddressCity;

    @FindBy(id = "shipping-state")
    private WebElement shippingAddressState;

    @FindBy(id = "shipping-postcode")
    private WebElement shippingAddressPostcode;

    @FindBy(id = "shipping-phone")
    private WebElement shippingAddressPhone;

    @FindBy(css = ".wc-block-checkout__use-address-for-billing .wc-block-components-checkbox__input")
    private WebElement useSameAddressCheckbox;

    @FindBy(css = "input#components-form-token-input-0")
    private WebElement countrySelect;

    @FindBy(css = ".wc-block-components-checkout-place-order-button")
    private WebElement placeOrderButton;

    @FindBy(css = ".order strong")
    private WebElement orderNumber;

    @FindBy(css = "p.woocommerce-notice")
    private WebElement orderReceivedMessage;

    @FindBy(css = ".wc-block-components-textarea")
    private WebElement orderNotesComments;

    @LazyAutowired
    private Utils utils;

    public void fillCheckoutForm(boolean virtualProduct, String firstName, String lastName, String email,
                                 String phone, String address1, String city,
                                 String postcode, String country, String state) {

        if (virtualProduct) {
            utils.waitClearSendKeys(billingAddressFirstName, firstName);
            utils.waitClearSendKeys(billingAddressLastName, lastName);
            utils.waitClearSendKeys(contactInformationEmailAddress, email);
            utils.waitClearSendKeys(billingAddressPhone, phone);
            utils.waitClearSendKeys(billingAddressAddress1, address1);
            utils.waitClearSendKeys(billingAddressCity, city);
            utils.waitClearSendKeys(countrySelect, country);
            countrySelect.sendKeys(Keys.ENTER);
            utils.waitClearSendKeys(billingAddressState, state);
            countrySelect.sendKeys(Keys.ENTER);
            utils.waitClearSendKeys(billingAddressPostcode, postcode);
        } else {
            utils.waitClearSendKeys(contactInformationEmailAddress, email);
            utils.waitClearSendKeys(shippingAddressFirstName, firstName);
            utils.waitClearSendKeys(shippingAddressLastName, lastName);
            utils.waitClearSendKeys(shippingAddressAddress1, address1);
            utils.waitClearSendKeys(shippingAddressCity, city);
            utils.waitClearSendKeys(shippingAddressPhone, phone);
            utils.waitClearSendKeys(countrySelect, country);
            countrySelect.sendKeys(Keys.ENTER);
            utils.waitClearSendKeys(shippingAddressState, state);
            countrySelect.sendKeys(Keys.ENTER);
            utils.waitClearSendKeys(shippingAddressPostcode, postcode);
            utils.scrollAndClickJs(useSameAddressCheckbox);
        }
        utils.scrollAndClickJs(orderNotesCheckbox);
        utils.waitClearSendKeys(orderNotesComments, "Please deliver before 5pm");
    }


    public void placeOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));
        utils.scrollAndClickJs(placeOrderButton);
    }

    public String getOrderNumber() {
        return utils.waitAndGetElementText(orderNumber);
    }

    public boolean isOrderReceivedMessageDisplayed() {
        return utils.isElementDisplayed(orderReceivedMessage);
    }

    public void waitForCheckoutPageToLoad() {
        wait.until(d -> placeOrderButton.isDisplayed());
    }

}
