package com.heima.weather.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Slf4j
@Component
public class ActionLogAspect {
    @Pointcut("execution(* com.heima.weather.service..*(..))")
    public void allMethods(){};
    //从哪里切入？
    @Around("allMethods()")
    public Object exeTimeAndCurTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        LocalDateTime now = LocalDateTime.now();
        String time = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh-mm-ss"));

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();

        log.info("执行耗时是"+(end-start)+"ms,调用方法时间是"+time);

        return result;


    }

}
