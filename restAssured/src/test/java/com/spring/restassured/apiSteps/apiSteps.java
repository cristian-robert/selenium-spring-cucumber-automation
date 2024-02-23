package com.spring.restassured.apiSteps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import com.spring.restassured.apiClient.RestAssuredConfig;
import com.spring.restassured.apiClient.models.Booking;
import com.spring.restassured.apiClient.models.BookingDates;
import com.spring.restassured.apiClient.models.BookingId;
import com.spring.restassured.apiClient.models.NewBooking;
import com.spring.restassured.context.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;

@CucumberContextConfiguration
@SpringBootTest
public class apiSteps {
    @Autowired
    private RestAssuredConfig restAssuredConfig;

    @Autowired
    private ScenarioContext scenarioContext;

    private String bookingId;
    private Faker faker = new Faker();


    @Given("I make a request to get all bookings")
    public void iMakeARequestToGetAllBookings() {
        List<BookingId> response = given()
                .spec(restAssuredConfig.requestSpecificationWithoutAuth())
                .get()
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .extract()
                .body()
                .jsonPath()
                .getList(".", BookingId.class);
        scenarioContext.set("response", response);
    }

    @Then("I should see all bookings")
    public void iShouldSeeAllBookings() {
        List<BookingId> response = (List<BookingId>) scenarioContext.get("response");
        Assert.assertTrue(!response.isEmpty());
    }

    @Then("I should see a random booking")
    public void iShouldSeeARandomBooking() {
        List<BookingId> response = (List<BookingId>) scenarioContext.get("response");
        Random random = new Random();
        Booking booking = given()
                .spec(restAssuredConfig.requestSpecificationWithoutAuth())
                .get("/" + response.get(random.nextInt(response.size())).getBookingid())
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .extract()
                .body()
                .as(Booking.class);
        Assert.assertFalse(booking.getFirstname().isEmpty());
    }

    @Given("I have a new booking details")
    public void iHaveANewBookingDetails() {
        scenarioContext.set("bookingDetails", createBooking());
    }

    @When("I make a POST request to create a booking receiving a {int} status code")
    public void iMakeAPOSTRequestToCreateABookingReceiveingAStatusCode(int statusCode) {
        Booking bookingDetails = (Booking) scenarioContext.get("bookingDetails");
        NewBooking response = given()
                .spec(restAssuredConfig.requestSpecificationWithoutAuth())
                .body(bookingDetails)
                .post()
                .then()
                .assertThat()
                .statusCode(statusCode)
                .and()
                .extract()
                .body()
                .as(NewBooking.class);
        scenarioContext.set("bookingResponse", response);
    }


    @Then("the booking should be created with the correct details")
    public void theBookingShouldBeCreatedWithTheCorrectDetails() {
        Booking bookingDetails = (Booking) scenarioContext.get("bookingDetails");
        NewBooking bookingResponse = (NewBooking) scenarioContext.get("bookingResponse");

        Assert.assertEquals(bookingResponse.getBooking().getFirstname(), bookingDetails.getFirstname());
        Assert.assertEquals(bookingResponse.getBooking().getLastname(), bookingDetails.getLastname());
        Assert.assertEquals(bookingResponse.getBooking().getTotalprice(), bookingDetails.getTotalprice());
        Assert.assertEquals(bookingResponse.getBooking().isDepositpaid(), bookingDetails.isDepositpaid());
        Assert.assertEquals(bookingResponse.getBooking().getBookingdates().getCheckin(), bookingDetails.getBookingdates().getCheckin());
        Assert.assertEquals(bookingResponse.getBooking().getBookingdates().getCheckout(), bookingDetails.getBookingdates().getCheckout());
        Assert.assertEquals(bookingResponse.getBooking().getAdditionalneeds(), bookingDetails.getAdditionalneeds());
    }

    private Booking getBookingDetails() {
        return given()
                .spec(restAssuredConfig.requestSpecificationWithoutAuth())
                .get("/" + scenarioContext.get("randomBookingId"))
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .extract()
                .body()
                .as(Booking.class);
    }


    @Given("I make a GET request to retrieve a random booking")
    public void iMakeAGETRequestToRetrieveARandomBooking() {
        iMakeARequestToGetAllBookings();
        List<BookingId> response = (List<BookingId>) scenarioContext.get("response");
        scenarioContext.set("randomBookingId", response.get(new Random().nextInt(response.size())));
        getBookingDetails();
        scenarioContext.set("bookingDetails", getBookingDetails());
    }


    @When("I make a PUT request to update the previous booking")
    public void iMakeAPUTRequestToUpdateThePreviousBooking() {
        scenarioContext.set("bookingUpdate", createBooking(faker.name().firstName(),
                faker.name().lastName(), faker.number().numberBetween(100, 1000),
                faker.bool().bool(), "2023-11-11", "2023-12-12",
                faker.lorem().sentence()));

        Booking response = given()
                .spec(restAssuredConfig.requestSpecificationWithAuth())
                .body(scenarioContext.get("bookingUpdate"))
                .put("/" + scenarioContext.get("randomBookingId"))
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .extract()
                .body()
                .as(Booking.class);
        scenarioContext.set("updatedBooking", response);
    }

    private Booking createBooking() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        int totalPrice = faker.number().numberBetween(100, 1000);
        boolean depositPaid = faker.bool().bool();
        String checkIn = "2023-07-07";
        String checkOut = "2023-08-08";
        String additionalNeeds = faker.lorem().sentence();

        BookingDates bookingDates = new BookingDates(checkIn, checkOut);
        return new Booking(firstName, lastName, totalPrice, depositPaid, bookingDates, additionalNeeds);
    }

    private Booking createBooking(String name, String lastName, int totalPrice,
                                                                       boolean depositPaid, String checkIn, String checkOut, String additionalNeeds) {

        BookingDates bookingDates = new BookingDates(checkIn, checkOut);
        return new Booking(name, lastName, totalPrice, depositPaid, bookingDates, additionalNeeds);
    }

    @Then("the booking should be updated with the new details")
    public void theBookingShouldBeUpdatedWithTheNewDetails() {
        Booking bookingUpdate = (Booking) scenarioContext.get("bookingUpdate");
        Booking updatedBooking = (Booking) scenarioContext.get("updatedBooking");

        Assert.assertEquals(bookingUpdate.getFirstname(), updatedBooking.getFirstname());
        Assert.assertEquals(bookingUpdate.getLastname(), updatedBooking.getLastname());
        Assert.assertEquals(bookingUpdate.getTotalprice(), updatedBooking.getTotalprice());
        Assert.assertEquals(bookingUpdate.isDepositpaid(), updatedBooking.isDepositpaid());
        Assert.assertEquals(bookingUpdate.getBookingdates().getCheckin(), updatedBooking.getBookingdates().getCheckin());
        Assert.assertEquals(bookingUpdate.getBookingdates().getCheckout(), updatedBooking.getBookingdates().getCheckout());
        Assert.assertEquals(bookingUpdate.getAdditionalneeds(), updatedBooking.getAdditionalneeds());
    }

    @Given("I make a PATCH request to partially update the previous booking")
    public Boolean iMakeAPATCHRequestToPartiallyUpdateThePreviousBooking() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String newAdditionalNeeds = faker.lorem().sentence();
        Map<String, String> patchBody = Collections.singletonMap("additionalneeds", newAdditionalNeeds);

        String patchBodyJson = objectMapper.writeValueAsString(patchBody);
        return given()
                .spec(restAssuredConfig.requestSpecificationWithAuth())
                .body(patchBodyJson)
                .patch("/" + ((NewBooking) scenarioContext.get("bookingResponse")).getBookingid())
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .extract()
                .path("additionalneeds")
                .equals(newAdditionalNeeds);
    }

    @Then("the specified booking details should be updated")
    public void theSpecifiedBookingDetailsShouldBeUpdated() throws JsonProcessingException {
        Assert.assertTrue(iMakeAPATCHRequestToPartiallyUpdateThePreviousBooking());
    }

    @When("I make a DELETE request to remove the previous booking")
    public void iMakeADELETERequestToRemoveThePreviousBooking() {
        int statusCode = given()
                .spec(restAssuredConfig.requestSpecificationWithAuth())
                .log().all()
                .delete("/" + scenarioContext.get("lastBookingId"))
                .andReturn()
                .statusCode();
        scenarioContext.set("deleteBookingStatusCode", statusCode);
    }

    @Then("I should receive a {int} status code")
    public void iShouldReceiveAStatusCode(int statusCode) {
        Assert.assertEquals(scenarioContext.get("deleteBookingStatusCode"), statusCode);
    }

    @And("the booking should no longer exist")
    public void theBookingShouldNoLongerExist() {
        given()
                .spec(restAssuredConfig.requestSpecificationWithoutAuth())
                .get("/" + scenarioContext.get("lastBookingId"))
                .then()
                .assertThat()
                .statusCode(404);
    }

}
