package com.example.library.proxy;

import com.example.library.aspect.LibraryAdvice;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyFactory implements MethodInterceptor {

    private final Object target;
    private final LibraryAdvice advice;

    public CglibProxyFactory(Object target, LibraryAdvice advice) {
        this.target = target;
        this.advice = advice;
    }

    public Object createProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Object result = null;
        try {
            advice.checkPermission();
            System.out.println("[CGLIB Proxy] Before method: " + method.getName());
            result = method.invoke(target, args);
            advice.writeLog();
        } catch (Throwable ex) {
            Throwable realEx = ex.getCause() != null ? ex.getCause() : ex;
            advice.handleException(realEx);
            throw realEx;
        }
        return result;
    }
}