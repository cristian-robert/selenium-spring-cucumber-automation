package com.spring.restassured.apiClient;

import com.spring.restassured.context.ScenarioContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ResultConsumerService {

    @Autowired
    private ScenarioContext scenarioContext;

    @KafkaListener(topics = "resultConsumer", groupId = "result-group")
    public void listen(String message) {
        scenarioContext.set("result", message);
    }
}
