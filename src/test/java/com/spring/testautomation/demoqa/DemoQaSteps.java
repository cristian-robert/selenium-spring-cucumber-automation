package com.spring.testautomation.demoqa;

import com.spring.testautomation.driver.annotations.LazyAutowired;
import com.spring.testautomation.page.demoqa.mainPage.DemoQaMainPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

import java.util.Objects;

/**
 * @author cristian_iosef
 */
@SpringBootTest
@CucumberContextConfiguration
public class DemoQaSteps {
    
    @LazyAutowired
    private DemoQaMainPage demoQaMainPage;

    @Given("I am on the homepage")
    public void iAmOnTheHomepage() {
        demoQaMainPage.goTo();
        Assert.assertTrue(demoQaMainPage.isAt());
    }

    @When("I click on {string}")
    public void iClickOn(String homepageSection) {
        demoQaMainPage.getMainPageSections().clickSection(homepageSection);
    }

    @Then("I should be on the {string} page")
    public void iShouldBeOnThePage(String pageName) {
        Assert.assertTrue(demoQaMainPage.isOnSelectedPage(pageName));
    }

    @Given("I am on {string} page")
    public void iAmOnPage(String page) {
        demoQaMainPage.goToPage(page.toLowerCase());
        Assert.assertTrue(demoQaMainPage.isOnSelectedPage(page.toLowerCase()));
    }

    @When("I see {string} dropdown is not collapsed")
    public void iSeeDropdownIsNotCollapsed(String dropdownName) {
        demoQaMainPage.getMainPageSections().expandDropdownIfNotExpanded(dropdownName);
    }

    @And("I click on {string} option")
    public void iClickOnOption(String option) {
        demoQaMainPage.getMainPageSections().clickOption(option);
    }

    @When("I fill full name as {string}")
    public void iFillFullNameAs(String fullName) {
        Assert.assertTrue(demoQaMainPage.getTextBoxSection().isAt());
        demoQaMainPage.getTextBoxSection().fillFullName(fullName);
    }

    @And("I fill email as {string}")
    public void iFillEmailAs(String email) {
        demoQaMainPage.getTextBoxSection().fillEmail(email);
    }

    @And("I fill current address as {string}")
    public void iFillCurrentAddressAs(String currentAddress) {
        demoQaMainPage.getTextBoxSection().fillCurrentAddress(currentAddress);
    }

    @And("I fill permanent address as {string}")
    public void iFillPermanentAddressAs(String permanentAddress) {
        demoQaMainPage.getTextBoxSection().fillPermanentAddress(permanentAddress);
    }

    @And("I click on submit button")
    public void iClickOnSubmitButton() {
        demoQaMainPage.getTextBoxSection().clickSubmit();
    }

    @Then("I validate the {string} {string} {string} {string}")
    public void iValidateThe(String fullName, String email, String currentAddress, String permanentAddress) {
        Assert.assertTrue(demoQaMainPage.getTextBoxSection().verifySubmittedData(fullName, email, currentAddress, permanentAddress));
    }

    @Then("I check for field errors")
    public void iCheckForFieldErrors() {
        Assert.assertTrue(demoQaMainPage.getTextBoxSection().checkFieldErrorsDisplayed());
    }

    @When("I click on the {string} dropdown")
    public void iClickOnTheDropdown(String dropdownName) {
        if(Objects.equals(dropdownName, "home")){
            if(demoQaMainPage.getCheckboxSection().checkAllSectionsCollapsed()) {
                demoQaMainPage.getCheckboxSection().clickDropdown(dropdownName);
            }
        } else {
            demoQaMainPage.getCheckboxSection().clickDropdown(dropdownName);
        }
    }

    @And("I click on the {string} checkbox")
    public void iClickOnTheCheckbox(String checkboxName) {
        demoQaMainPage.getCheckboxSection().clickCheckbox(checkboxName);
    }

    @Then("I see I have selected {string} {string} checkboxes")
    public void iSeeIHaveSelectedCheckboxes(String checkbox1, String checkbox2) {
        Assert.assertTrue(demoQaMainPage.getCheckboxSection().checkSuccessMessage(checkbox1));
        Assert.assertTrue(demoQaMainPage.getCheckboxSection().checkSuccessMessage(checkbox2));
    }
}
