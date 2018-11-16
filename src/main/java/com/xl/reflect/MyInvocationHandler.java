package com.xl.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import lombok.extern.log4j.Log4j;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-04-13
 * @Time: 16:30
 * To change this template use File | Settings | File Templates.
 */
@Log4j
class MyInvocationHandler implements InvocationHandler {
    private Object obj = null;
    
    public Object bind(Object obj) {
        this.obj = obj;
        log.info("开始代理");
        return java.lang.reflect.Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }
    
    /**
     * 调用代理的方法所执行的方法
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("代理执行方法前");
        Object temp = method.invoke(this.obj, args);
        log.info("代理执行方法后");
        return temp;
    }
}
