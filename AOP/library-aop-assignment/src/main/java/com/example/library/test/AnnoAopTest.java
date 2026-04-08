package com.example.library.test;

import com.example.library.dao.BookDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnoAopTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-anno.xml");

        BookDao bookDao = context.getBean(BookDao.class);

        System.out.println("---- Call addBook() ----");
        bookDao.addBook();

        System.out.println("---- Call deleteBook() ----");
        bookDao.removeBook();
    }
}