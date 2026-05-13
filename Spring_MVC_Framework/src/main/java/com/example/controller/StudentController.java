package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/home")
    public String home() {
        return "studentHome";
    }

    @GetMapping("/list")
    public String list() {
        return "studentList";
    }

    @GetMapping("/add")
    public String add() {
        return "studentAdd";
    }
}
