package com.spring.testautomation.scope;

import org.springframework.stereotype.Component;

/**
 * @author cristian_iosef
 */
@Component
public class SeniorEngineer {
    private Salary salary;

    public Salary getSalary() {
        return salary;
    }

    public void setAmount(int amount) {
        this.salary.setAmount(amount);
    }
}
