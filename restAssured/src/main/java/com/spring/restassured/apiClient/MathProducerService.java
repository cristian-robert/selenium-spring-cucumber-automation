package com.spring.restassured.apiClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MathProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendNumbers(String topic, int number1, int number2, String method) {
        String message = number1 + "," + number2 + "," + method;
        kafkaTemplate.send(topic, message);
    }
}
