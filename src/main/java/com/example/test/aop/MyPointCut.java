package com.example.test.aop;

import com.example.test.model.Operation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class MyPointCut {

//    To all methods with all return types
//    @Pointcut(value = "execution(* com.example.test.model.Operation.k(..))")
//    public void log() {}

    @Before("execution(* com.example.test.model.Operation.k(..))")
    public void before(JoinPoint joinPoint) {
        log.info("additional logs");
        log.info("Method Signature: {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "execution(* com.example.test.model.Operation.*(..))", returning = "result")
    public void after(JoinPoint joinPoint, Object result) {
        log.info("after");
        log.info("Method Signature: {}", joinPoint.getSignature());
        log.info("result {}", result);
    }
    @Before("@annotation(com.example.test.annotation.LogMetric)")
    public void beforeAnnotation(JoinPoint joinPoint) {
        log.info("before annotations");
        log.info("Method Signature: {}", joinPoint.getSignature());
    }

    @Around("@annotation(com.example.test.annotation.LogMetric)")
    public Object aroundLogMetrics(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object var1 = proceedingJoinPoint.proceed();
        log.info("time consumed: {}", System.currentTimeMillis() - start);
        return var1 + "1";
    }
}
