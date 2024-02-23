package com.spring.common.context;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CucumberSpringConfiguration {

    @Bean
    public static CustomScopeConfigurer scenarioScopeConfigurer() {
        CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        configurer.addScope("scenario", new ScenarioScope());
        return configurer;
    }
}
