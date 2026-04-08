package com.example.library.proxy;

import com.example.library.advice.LibraryAdvice;
import com.example.library.admin.LibraryAdmin;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    private final LibraryAdvice advice;

    public CglibProxy(LibraryAdvice advice) {
        this.advice = advice;
    }

    public LibraryAdmin createProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(LibraryAdmin.class);
        enhancer.setCallback(this);
        return (LibraryAdmin) enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object result = null;

        try {
            advice.checkPermission();
            advice.writeLog();

            result = methodProxy.invokeSuper(obj, args);

            advice.afterLog();
        } catch (Throwable e) {
            advice.handleException(e);
        }

        return result;
    }
}