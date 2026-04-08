package com.example.library.test;

import com.example.library.advice.LibraryAdvice;
import com.example.library.admin.LibraryAdmin;
import com.example.library.proxy.CglibProxy;

public class CglibTest {

    public static void main(String[] args) {
        LibraryAdvice advice = new LibraryAdvice();

        CglibProxy proxyFactory = new CglibProxy(advice);
        LibraryAdmin proxy = proxyFactory.createProxy();

        System.out.println("---- Call openLibrary() ----");
        proxy.openLibrary();

        System.out.println("---- Call closeLibrary() ----");
        proxy.closeLibrary();
    }
}