package com.spring.restassured.apiClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MathConsumerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "mathProducer", groupId = "math-group")
    public void listen(String message) {
        String[] parts = message.split(",");
        if (parts.length < 3) return; // Ensure message format is correct

        int number1 = Integer.parseInt(parts[0]);
        int number2 = Integer.parseInt(parts[1]);
        String method = parts[2];

        int result = switch (method) {
            case "add" -> add(number1, number2);
            case "multiply" -> multiply(number1, number2);
            default -> 0; // Handle unknown methods or throw an exception
        };

        // Send the result to topic2
        kafkaTemplate.send("resultConsumer", String.valueOf(result));
    }

    private int add(int number1, int number2) {
        return number1 + number2;
    }

    private int multiply(int number1, int number2) {
        return number1 * number2;
    }

    // Add more methods as needed
}
