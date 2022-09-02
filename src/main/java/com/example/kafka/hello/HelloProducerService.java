package com.example.kafka.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class HelloProducerService {
    
    @Value("${spring.kafka.topic.name}")
    private String topicName;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String message) {

        Message<String> kafkaMessage = MessageBuilder
            .withPayload(message)
            .setHeader(KafkaHeaders.TOPIC, topicName)
            .build();

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(kafkaMessage);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("kafka produce result: {}", result.getProducerRecord().value()); 
            }

            @Override
            public void onFailure(Throwable ex) {
                log.info("kafka produce fail: {}", ex.getMessage()); 
            }
        });
    }

}
