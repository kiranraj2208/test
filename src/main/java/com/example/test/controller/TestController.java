package com.example.test.controller;

import com.example.test.annotation.LogMetric;
import com.example.test.model.Operation;
import com.example.test.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

@RestController
@Slf4j
public class TestController {

    Random random = new Random();
    public static final String STATUS = "status";
    public static final String MESSAGE = "message";
    final Operation operation;

    public TestController(Operation operation) {
        this.operation = operation;
    }

    @GetMapping("/get")
    public void get() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String s = restTemplate.getForObject("http://localhost:3000/get", String.class);
        Product product = new ObjectMapper().readValue(s, Product.class);
        LinkedHashMap<String, Object> l = (LinkedHashMap<String, Object>) product.getDetails("result").get("inside");
        log.info("{}", ((LinkedHashMap<String, Object>) l.get("522")).get("name"));
        log.info(s);
    }

    @GetMapping("/status")
    public String getStatus() {
        log.info("from k: {}", operation.k());
        operation.m();
        operation.msg();
        logMetrics();
        return "Active";
    }

    @GetMapping("/response")
    public Map<String, String> getResponse() {
        Map<String, String> map = new HashMap<>();
        map.put(STATUS, "success3");
        map.put(MESSAGE, "you are in the test-main app. This is the latest main version...");
        log.info("success log: {}", map);
        return map;
    }

    @GetMapping("/errors")
    public ResponseEntity<Map<String, String>> getErrorResponse() {
        Map<String, String> map = new HashMap<>();
        map.put(STATUS, "error");
        map.put(MESSAGE, "you are out");
        log.error("error log: {}", map);
        return ResponseEntity.status(random.nextInt(2) == 1 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR).body(map);
    }

    @GetMapping("/errors1")
    public ResponseEntity<Map<String, String>> getErrorResponse1() {
        Map<String, String> map = new HashMap<>();
        map.put(STATUS, "error");
        map.put(MESSAGE, "you are really out.");
        log.error("error log: {}", map);
        return ResponseEntity.status(random.nextInt(2) == 1 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR).body(map);
    }

    @GetMapping("/log")
    public Map<String, String> getLog() {
        Map<String, String> map = new HashMap<>();
        map.put(STATUS, "logs1");
        map.put(MESSAGE, "you are in the new logging mood 11");
        log.info("The logging count {}", random.nextInt(100));
        return map;
    }

    @LogMetric
    public void logMetrics() {
        System.out.println("In the log metrics function");
    }
}
