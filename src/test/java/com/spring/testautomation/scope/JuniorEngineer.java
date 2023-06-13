package com.spring.testautomation.scope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author cristian_iosef
 */
@Component
@Scope("prototype")
public class JuniorEngineer {
    @Autowired
    private Salary salary;

    public Salary getSalary() {
        return salary;
    }

    public void setAmount(int amount) {
        salary.setAmount(amount);
    }
}
