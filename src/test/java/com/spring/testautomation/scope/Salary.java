package com.spring.testautomation.scope;

import org.springframework.stereotype.Component;

/**
 * @author cristian_iosef
 */
@Component
public class Salary {

    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
