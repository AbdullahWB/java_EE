package com.example.library.advice;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnnoLibraryAdvice {

    @Before("execution(* com.example.library.dao.BookDaoImpl.*(..))")
    public void beforeAdvice() {
        System.out.println("[Before] Permission check passed.");
    }

    @AfterReturning("execution(* com.example.library.dao.BookDaoImpl.*(..))")
    public void afterReturningAdvice() {
        System.out.println("[AfterReturning] Method executed successfully.");
    }

    @AfterThrowing(pointcut = "execution(* com.example.library.dao.BookDaoImpl.*(..))", throwing = "e")
    public void afterThrowingAdvice(Exception e) {
        System.out.println("[AfterThrowing] Exception occurred: " + e.getMessage());
    }
}