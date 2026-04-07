package com.example.library.proxy;

import com.example.library.aspect.LibraryAdvice;
import com.example.library.dao.BookDao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyFactory {

    public static BookDao createProxy(BookDao target, LibraryAdvice advice) {
        return (BookDao) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object result = null;
                        try {
                            advice.checkPermission();
                            System.out.println("[JDK Proxy] Before method: " + method.getName());
                            result = method.invoke(target, args);
                            advice.writeLog();
                        } catch (Throwable ex) {
                            Throwable realEx = ex.getCause() != null ? ex.getCause() : ex;
                            advice.handleException(realEx);
                            throw realEx;
                        }
                        return result;
                    }
                });
    }
}