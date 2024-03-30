package com.spring.restassured.apiClient;

import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
@EnableKafka
public class KafkaConsumerService {
    private final List<String> messages = Collections.synchronizedList(new LinkedList<>());


    private CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(topics = "test_spring_topic", groupId = "test-group")
    public void listen(String message) {
        messages.add(message);
        latch.countDown(); // Decrement the count of the latch, releasing all waiting threads when count reaches zero.
    }

    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }

    public boolean waitForMessage(long timeout, TimeUnit unit) throws InterruptedException {
        return latch.await(timeout, unit); // Returns true if the count reached zero and false if the waiting time elapsed before the count reached zero
    }

    public void resetLatch(int count) {
        latch = new CountDownLatch(count); // Reset the latch with the specific count for the next test
    }
}

