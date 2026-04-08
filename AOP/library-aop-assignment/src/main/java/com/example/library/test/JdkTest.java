package com.example.library.test;

import com.example.library.advice.LibraryAdvice;
import com.example.library.dao.BookDao;
import com.example.library.dao.BookDaoImpl;
import com.example.library.proxy.JdkProxy;

public class JdkTest {

    public static void main(String[] args) {
        BookDao target = new BookDaoImpl();
        LibraryAdvice advice = new LibraryAdvice();

        JdkProxy proxyFactory = new JdkProxy(target, advice);
        BookDao proxy = (BookDao) proxyFactory.createProxy();

        System.out.println("---- Call addBook() ----");
        proxy.addBook();

        System.out.println("---- Call deleteBook() ----");
        proxy.removeBook();

    }

}
