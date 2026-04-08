package com.example.library.advice;

public class LibraryAdvice {

    public void checkPermission() {
        System.out.println("[Permission] Permission check passed.");
    }

    public void writeLog() {
        System.out.println("[Log] Method execution started.");
    }

    public void afterLog() {
        System.out.println("[Log] Method execution finished.");
    }

    public void handleException(Throwable e) {
        System.out.println("[Exception] " + e.getClass().getSimpleName()
                + ": " + e.getMessage());
    }
}
