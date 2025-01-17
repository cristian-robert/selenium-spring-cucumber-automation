package com.spring.selenium.wpSteps;

import com.github.javafaker.Faker;
import com.spring.common.annotations.LazyAutowired;
import com.spring.selenium.context.ScenarioContext;
import com.spring.selenium.page.wp.CartPage;
import com.spring.selenium.page.wp.CheckoutPage;
import com.spring.selenium.page.wp.HomePage;
import com.spring.selenium.page.wp.MyAccountPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testng.Assert;

@SpringBootTest
@CucumberContextConfiguration
public class WpSteps{

    @LazyAutowired
    private HomePage homePage;

    @LazyAutowired
    private CartPage cartPage;

    @LazyAutowired
    private CheckoutPage checkoutPage;

    @LazyAutowired
    private MyAccountPage myAccountPage;


    private final Faker faker = new Faker();

    @LazyAutowired
    private JdbcTemplate jdbcTemplate;
    @LazyAutowired
    private ScenarioContext scenarioContext;

    private final String firstName = faker.name().firstName();
    private final String lastName = faker.name().lastName();
    private final String emailAddress = faker.internet().emailAddress();
    private final String cellPhone = faker.phoneNumber().cellPhone();
    private final String fullAddress = faker.address().fullAddress();
    private final String city = "London";
    private final String postalCode = "SW1A 1AA";
    private final String country = "United Kingdom";
    private final String state = "London";
    private boolean isVirtualProduct = false;

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        myAccountPage.goTo();
    }

    @When("I login with {string} and {string}")
    public void iLoginWithAnd(String username, String password) {
        myAccountPage.getMyAccountLoginFormPageFragment().login(username, password);
    }

    @Then("I should see my account page logged in as {string}")
    public void iShouldSeeMyAccountPageLoggedInAs(String username) {
        Assert.assertTrue(myAccountPage.getMyAccountLoggedInPageFragment().getWelcomeMessage().contains(username));
    }

    @Then("I should see an error message {string}")
    public void iShouldSeeAnErrorMessageErrorThePasswordYouEnteredForTheUsernameTestAutomationIsIncorrectLostYourPassword(String errorMsg) {
        Assert.assertTrue(myAccountPage.getMyAccountLoginFormPageFragment().getLoginError().contains(errorMsg));
    }

    @Given("I am on the register page")
    public void iAmOnTheRegisterPage() {
        myAccountPage.goTo();
    }

    @When("I fill in the register form with random data")
    public void iFillInTheRegisterFormWithRandomData() {
        myAccountPage.getMyAccountRegisterFormPageFragment().fillRegisterForm(faker.name().username(),
                faker.internet().emailAddress(),
                faker.lorem().characters(15, true));
    }

    @And("I press Register")
    public void iPressRegister() {
        myAccountPage.getMyAccountRegisterFormPageFragment().clickRegister();
    }

    @Then("I should see logout button from my account page")
    public void iShouldSeeLogoutButtonFromMyAccountPage() {
        Assert.assertTrue(myAccountPage.getMyAccountLoggedInPageFragment().waitUserToBeLoggedIn());
    }

    @Given("I add a product to cart")
    public void iAddAProductToCart() throws Exception {
        homePage.goTo();
        homePage.getHomePageItemsFragment().clickRandomAddToCartButton();
    }

    @And("I access cart page")
    public void iAccessCartPage() {
        homePage.getHomePageHeaderFragment().waitForCartItemCount(1);
        homePage.getHomePageHeaderFragment().clickCart();
        Assert.assertTrue(cartPage.getCartPageMainFragment().waitCartPageToLoad());
        Assert.assertEquals(cartPage.getCartPageMainFragment().getCartItemsCount(), 1);
        isVirtualProduct = cartPage.getCartPageMainFragment().checkProductDescriptionContainsVirtual(0);
    }

    @When("I apply coupon code {string}")
    public void iApplyCouponCode(String couponCode) {
        cartPage.getCartPageMainFragment().applyCoupon(couponCode);
    }

    @Then("I should see coupon code {string} applied successfully")
    public void iShouldSeeCouponCodeAppliedSuccessfully(String couponCode) {
        Assert.assertTrue(cartPage.getCartPageMainFragment()
                .getDiscountCodeApplied().contains(couponCode));
    }

    @And("when I proceed to checkout")
    public void whenIProceedToCheckout() {
        cartPage.getCartPageMainFragment().selectFreeShipping(isVirtualProduct);
        cartPage.getCartPageMainFragment().proceedToCheckout();
    }

    @And("fill checkout form with valid data")
    public void fillCheckoutFormWithValidData() {
        checkoutPage.getCheckoutPageMainFragment().waitForCheckoutPageToLoad();
        checkoutPage.getCheckoutPageMainFragment().fillCheckoutForm(isVirtualProduct,
                firstName,
                lastName,
                emailAddress,
                cellPhone,
                fullAddress,
                city,
                postalCode,
                country,
                state
                );
    }

    @And("click on place order button")
    public void clickOnButton() {
        checkoutPage.getCheckoutPageMainFragment().placeOrder();
    }

    @Then("I should see order confirmation page")
    public void iShouldSeeOrderConfirmationPage() {
        Assert.assertTrue(checkoutPage.getCheckoutPageMainFragment().isOrderReceivedMessageDisplayed());
    }

    @And("I should see order number")
    public void iShouldSeeOrderNumber() {
        scenarioContext.set("orderNumber", checkoutPage.getCheckoutPageMainFragment().getOrderNumber());
    }

    @And("I should see order in database")
    public void iShouldSeeOrderInDatabase() {
        String sql = "SELECT * FROM wp_wc_orders WHERE id = " + scenarioContext.get("orderNumber");
        String email = jdbcTemplate.queryForMap(sql).get("billing_email").toString();
        Assert.assertEquals(email, emailAddress);
    }

    @And("I check for cart to have {int} items")
    public void iCheckForCartToHaveItems(int count) {

        homePage.getHomePageHeaderFragment().removeAllItemsFromCart();
    }
}
