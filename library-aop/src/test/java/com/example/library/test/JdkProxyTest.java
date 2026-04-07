package com.example.library.test;

import com.example.library.aspect.LibraryAdvice;
import com.example.library.dao.BookDao;
import com.example.library.dao.BookDaoImpl;
import com.example.library.proxy.JdkProxyFactory;
import org.junit.Test;

public class JdkProxyTest {

    @Test
    public void testJdkProxy() {
        BookDao target = new BookDaoImpl();
        LibraryAdvice advice = new LibraryAdvice();
        BookDao proxy = JdkProxyFactory.createProxy(target, advice);

        proxy.addBook();

        try {
            proxy.deleteBook();
        } catch (Exception e) {
            System.out.println("[Test] Exception captured in test.");
        }
    }
}