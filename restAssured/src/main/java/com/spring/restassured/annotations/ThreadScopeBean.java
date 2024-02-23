package com.spring.restassured.annotations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.lang.annotation.*;

/**
 * @author cristian_iosef
 */
@Bean
@Scope("prototype")
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ThreadScopeBean {
}
