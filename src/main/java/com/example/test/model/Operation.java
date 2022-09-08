package com.example.test.model;

import com.example.test.annotation.LogMetric;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Operation {
    public void msg() {
        log.info("This is the message");
    }

    public int m() {
        log.info("m method invoked");
        return 2;
    }

    @LogMetric
    public String k() {
        Integer k = 5;
        System.out.println(k.equals(5));
        log.info("k method invoked");
        return "3";
    }
}
