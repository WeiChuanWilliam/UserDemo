package com.rothur.UserDemo.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Aspect
@Component
@Slf4j // Using SLF4J for logging as we discussed!
public class UserAspect {

    // Pointcut: Matches all methods in your specific controller
    @Around("execution(* com.rothur.UserDemo.controller.UserController.*(..))")
    public Object monitorExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.info("Starting method: {} with arguments: {}", methodName, Arrays.toString(args));

        try {
            Object result = joinPoint.proceed();

            long timeTaken = System.currentTimeMillis() - startTime;
            log.info("Method {} finished successfully in {}ms. Result: {}", methodName, timeTaken, result);

            return result;
        } catch (Exception e) {
            log.error("Method {} failed after {}ms with error: {}", methodName, (System.currentTimeMillis() - startTime), e.getMessage());
            throw e; // Re-throw so the app's error handling still works
        }
    }
}
