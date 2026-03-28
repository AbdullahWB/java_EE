package com.example.chapter02.component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class CourseInfo {

    private String courseName = "Spring Framework Basics";

    public CourseInfo() {
        System.out.println("CourseInfo constructor called.");
    }

    @PostConstruct
    public void init() {
        System.out.println("CourseInfo initialized.");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("CourseInfo destroyed.");
    }

    public String getCourseName() {
        return courseName;
    }
}
