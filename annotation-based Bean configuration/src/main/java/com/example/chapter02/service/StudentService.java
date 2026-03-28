package com.example.chapter02.service;

import com.example.chapter02.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    public void registerStudent() {
        System.out.println("StudentService: registering student...");
        studentDao.saveStudent();
    }
}
