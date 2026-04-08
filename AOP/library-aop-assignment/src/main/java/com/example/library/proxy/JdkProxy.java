package com.example.library.proxy;

import com.example.library.advice.LibraryAdvice;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy implements InvocationHandler {

    private final Object target;
    private final LibraryAdvice advice;

    public JdkProxy(Object target, LibraryAdvice advice) {
        this.target = target;
        this.advice = advice;
    }

    public Object createProxy() {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;

        try {
            advice.checkPermission();
            advice.writeLog();

            result = method.invoke(target, args);

            advice.afterLog();
        } catch (Throwable e) {
            advice.handleException(e.getCause() != null ? e.getCause() : e);
        }

        return result;
    }
}
