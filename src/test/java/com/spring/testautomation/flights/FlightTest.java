package com.spring.testautomation.flights;

import com.spring.testautomation.SpringBaseTestNGTest;
import com.spring.testautomation.page.flights.FlightsPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author cristian_iosef
 */
public class FlightTest extends SpringBaseTestNGTest {

    @Autowired
    private FlightsPage flightsPage;

    @Autowired
    private FlightAppDetails flightAppDetails;


    @Test
    public void flightTest(){
        this.flightsPage.goTo(flightAppDetails.getUrl());
        Assert.assertTrue(this.flightsPage.isAt());
        Assert.assertEquals(this.flightsPage.getLabels(), flightAppDetails.getLabels());
    }
}
