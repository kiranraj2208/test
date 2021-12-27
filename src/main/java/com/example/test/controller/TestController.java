package com.example.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@Slf4j
public class TestController {

    Random random = new Random();
    public static final String STATUS = "status";
    public static final String MESSAGE = "message";
    @GetMapping("/response")
    public Map<String, String> getResponse() {
        Map<String, String> map = new HashMap<>();
        map.put(STATUS, "success");
        map.put(MESSAGE, "you are in the test app. This is the modified version");
        log.info("success log: {}", map);
//        String s = "^(?<time>\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3})\\s+(?<level>[^\\s]+)\\s+(\\[(?<service>[^,]*),(?<trace>[^,]*),(?<span>[^,]*),(?<exportable>[^\\]]*)\\]\\s+)?(?<pid>\\d+)\\s+---\\s+\\[(?<thread>[^\\]]+)\\]\\s+(?<source>[^\\s]+)\\s*:\\s+(?<message>.*)";
        return map;
    }

    @GetMapping("/errors")
    public Map<String, String> getErrorResponse() {
        Map<String, String> map = new HashMap<>();
        map.put(STATUS, "error");
        map.put(MESSAGE, "you are out");
        log.error("error log: {}", map);
        return map;
    }
    @GetMapping("/log")
    public Map<String, String> getLog() {
        Map<String, String> map = new HashMap<>();
        map.put(STATUS, "logs1");
        map.put(MESSAGE, "you are in the new logging mood 10");
        log.info("The logging count {}", random.nextInt(100));
        return map;
    }
}
