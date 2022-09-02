package com.example.kafka.hello;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HelloController {
    private final HelloProducerService producerService;

    @GetMapping("/push")
    public ResponseEntity<String> push(@RequestParam String message) {
        log.info("message: {}", message);
        producerService.send(message);
        return ResponseEntity.ok("success");
    }
}
