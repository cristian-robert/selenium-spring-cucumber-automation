package com.spring.testautomation.visa;

import com.spring.testautomation.SpringBaseTestNGTest;
import com.spring.testautomation.entity.Customer;
import com.spring.testautomation.page.visa.VisaRegistrationPage;
import com.spring.testautomation.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Date;

/**
 * @author cristian_iosef
 */
@EnableAutoConfiguration()
public class UserVisaTest extends SpringBaseTestNGTest {

    private static final Logger logger = LoggerFactory.getLogger(UserVisaTest.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VisaRegistrationPage visaRegistrationPage;

    @Test(dataProvider = "getData")
    public void visaTest(Customer customer) {
            this.visaRegistrationPage.goTo();
            this.visaRegistrationPage.setNames(customer.getFirstName(), customer.getLastName());
            this.visaRegistrationPage.setBirthDate(customer.getDob().toLocalDate());
            this.visaRegistrationPage.setContactDetails(customer.getEmail(), customer.getPhone());
            this.visaRegistrationPage.setComments(customer.getComments());
            this.visaRegistrationPage.submit();
            logger.info("Confirmation number #INFO : " + this.visaRegistrationPage.getConfirmationNumber());
            logger.warn("Confirmation number #WARN : " + this.visaRegistrationPage.getConfirmationNumber());
    }

    @DataProvider
    public Object[] getData(ITestContext context) {
        return this.customerRepository
                .findByDobBetween(
                        Date.valueOf(context.getCurrentXmlTest().getParameter("dobFrom")),
                        Date.valueOf(context.getCurrentXmlTest().getParameter("dobTo")))
                .stream()
                .limit(3)
                .toArray();
    }
}
