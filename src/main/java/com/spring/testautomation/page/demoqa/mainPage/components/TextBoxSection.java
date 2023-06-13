package com.spring.testautomation.page.demoqa.mainPage.components;

import com.spring.testautomation.driver.annotations.PageFragment;
import com.spring.testautomation.page.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @author cristian_iosef
 */
@PageFragment
public class TextBoxSection extends Base {

        @FindBy(id = "userName")
        private WebElement userName;

        @FindBy(id = "userEmail")
        private WebElement userEmail;

        @FindBy(id = "currentAddress")
        private WebElement currentAddress;

        @FindBy(id = "permanentAddress")
        private WebElement permanentAddress;

        @FindBy(id = "submit")
        private WebElement submit;

        @FindBy(xpath = "//p[@id='name']")
        private WebElement nameDisplayed;

        @FindBy(xpath = "//p[@id='email']")
        private WebElement emailDisplayed;

        @FindBy(xpath = "//p[@id='currentAddress']")
        private WebElement currentAddressDisplayed;

        @FindBy(xpath = "//p[@id='permanentAddress']")
        private WebElement permanentAddressDisplayed;

        @FindBy(xpath = "//input[contains(@class, 'field-error')]")
        private List<WebElement> fieldValidationErrors;

        @Override
        public boolean isAt() {
            return this.wait.until(d -> this.submit.isDisplayed());
        }

        public void fillFullName(String fullName){
            this.userName.sendKeys(fullName);
        }

        public void fillEmail(String email){
            this.userEmail.sendKeys(email);
        }

        public void fillCurrentAddress(String currentAddress){
            this.currentAddress.sendKeys(currentAddress);
        }

        public void fillPermanentAddress(String permanentAddress){
            this.permanentAddress.sendKeys(permanentAddress);
        }

        public void clickSubmit(){
            this.submit.click();
        }

        public boolean verifySubmittedData(String fullName, String email, String currentAddress, String permanentAddress){
            return this.wait.until(d -> this.nameDisplayed.getText().contains(fullName) &&
                    this.emailDisplayed.getText().contains(email) &&
                    this.currentAddressDisplayed.getText().contains(currentAddress) &&
                    this.permanentAddressDisplayed.getText().contains(permanentAddress));
        }

        public boolean checkFieldErrorsDisplayed(){
            return this.wait.until(d -> this.fieldValidationErrors.size() > 0);
        }
}
