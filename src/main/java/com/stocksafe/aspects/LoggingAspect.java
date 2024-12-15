package com.stocksafe.aspects;


import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {


    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.stocksafe.services.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {

        logger.info("Iniciando lógica de execução para: " + joinPoint.getSignature());
    }
}
