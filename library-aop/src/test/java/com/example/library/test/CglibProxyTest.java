package com.example.library.test;

import com.example.library.admin.LibraryAdmin;
import com.example.library.aspect.LibraryAdvice;
import com.example.library.proxy.CglibProxyFactory;
import org.junit.Test;

public class CglibProxyTest {

    @Test
    public void testCglibProxy() {
        LibraryAdmin target = new LibraryAdmin();
        LibraryAdvice advice = new LibraryAdvice();

        CglibProxyFactory factory = new CglibProxyFactory(target, advice);
        LibraryAdmin proxy = (LibraryAdmin) factory.createProxy();

        proxy.openLibrary();
        proxy.closeLibrary();
    }
}