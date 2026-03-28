package com.example.chapter02.dao;

import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {

    public void saveStudent() {
        System.out.println("StudentDao: student information saved successfully.");
    }
}
