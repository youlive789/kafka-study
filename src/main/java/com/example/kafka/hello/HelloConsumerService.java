package com.example.kafka.hello;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HelloConsumerService {
    
    @KafkaListener(topics = "${spring.kafka.topic.name}", 
        groupId = "${spring.kafka.topic.group-name}", 
        containerFactory = "consumer")
    public void consume(@Payload String message, @Headers MessageHeaders headers) {
        log.info("Message Consumed: {}, headers: {}", message, headers);
    }

}
