package com.example.library.test;

import com.example.library.config.AppConfig;
import com.example.library.dao.BookDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAnnotationAopTest {

    @Test
    public void testSpringAnnotationAop() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        BookDao bookDao = context.getBean(BookDao.class);

        bookDao.addBook();

        try {
            bookDao.deleteBook();
        } catch (Exception e) {
            System.out.println("[Test] Exception captured in annotation AOP test.");
        }
    }
}
