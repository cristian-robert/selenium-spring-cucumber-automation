package com.spring.testautomation.scope;

import com.spring.testautomation.SpringBaseTestNGTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * @author cristian_iosef
 */
public class JuniorSeniorTest extends SpringBaseTestNGTest {
    @Autowired
    private JuniorEngineer juniorEngineer;

    @Autowired
    private SeniorEngineer seniorEngineer;

    @Test
    public void scopeTest(){
        juniorEngineer.setAmount(100);
        System.out.println("Junior Engineer salary: " + juniorEngineer.getSalary().getAmount());
        this.seniorEngineer.setAmount(200);
        System.out.println("Senior Engineer salary: " + seniorEngineer.getSalary().getAmount());
        System.out.println("Junior Engineer salary: " + juniorEngineer.getSalary().getAmount());
    }
}
