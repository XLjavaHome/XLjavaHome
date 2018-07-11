package com.xl.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-04-13
 * @Time: 16:30
 * To change this template use File | Settings | File Templates.
 */
class MyInvocationHandler implements InvocationHandler {
    private Object obj = null;

    public Object bind(Object obj) {
        this.obj = obj;
        System.out.println("bind");
        return java.lang.reflect.Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke");
        Object temp = method.invoke(this.obj, args);
        return temp;
    }
}
