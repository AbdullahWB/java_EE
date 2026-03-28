package com.example.chapter02;

import com.example.chapter02.component.CourseInfo;
import com.example.chapter02.config.AppConfig;
import com.example.chapter02.service.StudentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        StudentService studentService = context.getBean(StudentService.class);
        studentService.registerStudent();

        CourseInfo course1 = context.getBean(CourseInfo.class);
        CourseInfo course2 = context.getBean(CourseInfo.class);

        System.out.println("course1 == course2 : " + (course1 == course2));
        System.out.println(course1.getCourseName());

        context.close();
    }
}
