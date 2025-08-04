package org.example.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* org.example.service.*.*(..))")
    public void logBeforeServiceMethod(JoinPoint joinPoint) {
        log.info("Entering method: " + joinPoint.getSignature().toShortString());
    }

    @AfterReturning(pointcut = "execution(* org.example.service.*.*(..))", returning = "result")
    public void logAfterServiceMethod(JoinPoint joinPoint, Object result) {
        log.info("Exiting method: " + joinPoint.getSignature().toShortString() + " with result: " + result);
    }
}
