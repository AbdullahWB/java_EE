package com.example.library.test;

import com.example.library.dao.BookDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlAopTest {

    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml")) {

            BookDao bookDao = context.getBean("bookDao", BookDao.class);

            System.out.println("---- Call addBook() ----");
            bookDao.addBook();

            System.out.println("---- Call deleteBook() ----");
            try {
                bookDao.removeBook();
            } catch (ArithmeticException e) {
                System.out.println("[Test] Caught expected exception: " + e.getMessage());
            }
        }
    }
}
