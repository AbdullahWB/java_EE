package com.example.library.advice;

public class XmlLibraryAdvice {

    public void beforeAdvice() {
        System.out.println("[Before] Permission check passed.");
    }

    public void afterReturningAdvice() {
        System.out.println("[AfterReturning] Method executed successfully.");
    }

    public void afterThrowingAdvice(Exception e) {
        System.out.println("[AfterThrowing] Exception occurred: " + e.getMessage());
    }
}