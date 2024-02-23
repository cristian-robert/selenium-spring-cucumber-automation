package com.spring.restassured.apiClient;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.restassured.RestAssured.given;

@Configuration
public class RestAssuredConfig {

    @Value("${api.base.url}")
    private String baseUrl;
    @Value("${api.base.auth}")
    private String baseAuth;

    @Value("${api.base.path}")
    private String basePath;

    @Value("${api.username}")
    private String username;

    @Value("${api.password}")
    private String password;

    @Bean
    public RequestSpecification requestSpecificationWithAuth() {

        String token = given()
                .contentType("application/json")
                .baseUri(baseUrl)
                .body("{ \"username\": \"" + username + "\", \"password\": \"" + password + "\" }")
                .when()
                .post(baseAuth)
                .then()
                .extract()
                .path("token");
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setBasePath(basePath)
                .setAccept("application/json")
                .setContentType("application/json")
                .addCookie("token", token)
                .build();
    }

    @Bean
    public RequestSpecification requestSpecificationWithAuthBasic() {

        String token = given()
                .contentType("application/json")
                .baseUri(baseUrl)
                .body("{ \"username\": \"" + username + "\", \"password\": \"" + password + "\" }")
                .when()
                .post(baseAuth)
                .then()
                .extract()
                .path("token");
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setBasePath(basePath)
                .setAccept("application/json")
                .setContentType("application/json")
                .addHeader("Authorization", "Basic " + token)
                .build();
    }

    @Bean
    public RequestSpecification requestSpecificationWithoutAuth() {
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setBasePath(basePath)
                .setAccept("application/json")
                .setContentType("application/json")
                .build();
    }

}
