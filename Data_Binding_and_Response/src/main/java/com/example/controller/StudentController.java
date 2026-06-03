package com.example.controller;

import com.example.model.Course;
import com.example.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/info")
    @ResponseBody
    public String info(String name, int age) {
        System.out.println("Student name: " + name);
        System.out.println("Student age: " + age);
        return "Student info received";
    }

    @GetMapping("/search")
    @ResponseBody
    public String search(@RequestParam("studentName") String name) {
        System.out.println("Searching student: " + name);
        return "Search request received";
    }

    @GetMapping("/{id:\\d+}")
    @ResponseBody
    public String findById(@PathVariable("id") Integer id) {
        System.out.println("Student ID: " + id);
        return "Student ID received";
    }

    @PostMapping("/register")
    public String register(Student student, Model model) {
        model.addAttribute("student", student);
        return "result";
    }

    @PostMapping("/selectCourses")
    @ResponseBody
    public String selectCourses(@RequestParam("courses") List<String> courses) {
        System.out.println("Selected courses:");
        for (String course : courses) {
            System.out.println(course);
        }
        return "Courses received";
    }

    @PostMapping("/addCourse")
    @ResponseBody
    public String addCourse(@RequestBody Course course) {
        System.out.println("Course ID: " + course.getCourseId());
        System.out.println("Course Name: " + course.getCourseName());
        System.out.println("Credit: " + course.getCredit());
        return "Course received";
    }

    @GetMapping("/json")
    @ResponseBody
    public Student json() {
        Student student = new Student();
        student.setId(1001);
        student.setName("Tom");
        student.setAge(20);
        return student;
    }
}
