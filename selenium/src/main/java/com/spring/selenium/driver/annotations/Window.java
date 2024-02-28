package com.spring.selenium.driver.annotations;

import java.lang.annotation.*;

/**
 * @author cristian_iosef
 */
@Page
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Window {
    String value() default "";
}
