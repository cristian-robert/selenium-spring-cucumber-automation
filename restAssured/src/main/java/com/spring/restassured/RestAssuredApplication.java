package com.spring.restassured;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class RestAssuredApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestAssuredApplication.class, args);
    }

}
