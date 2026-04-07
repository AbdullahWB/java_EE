package com.example.library.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LibraryAdvice {

    @Pointcut("execution(* com.example.library.dao.BookDao.*(..))")
    public void bookDaoMethods() {
    }

    @Before("bookDaoMethods()")
    public void checkPermission() {
        System.out.println("[Permission] Checking user permission...");
    }

    @AfterReturning("bookDaoMethods()")
    public void writeLog() {
        System.out.println("[Log] Method execution finished.");
    }

    @AfterThrowing(pointcut = "bookDaoMethods()", throwing = "ex")
    public void handleException(Throwable ex) {
        System.out.println("[Exception] " + ex.getMessage());
    }
}
