package com.spring.testautomation.driver.annotations;

import java.lang.annotation.*;

/**
 * @author cristian_iosef
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TakeScreenshot {
}
