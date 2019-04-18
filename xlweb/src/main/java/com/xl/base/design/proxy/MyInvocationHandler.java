package com.xl.base.design.proxy;

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
public class MyInvocationHandler implements InvocationHandler {
    private Object obj = null;
    
    /**
     * 代理对象传class不行，必须传对象
     *
     * @param obj
     * @return
     */
    public Object bind(Object obj) {
        this.obj = obj;
        log.info("开始代理");
        return java.lang.reflect.Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }
    
    /**
     * 调用代理的方法所执行的方法
     * 1.如果在方法之前返回则不调用目标方法,会返回返回值
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        log.info("代理执行方法前");
        Object temp = null;
        temp = method.invoke(this.obj, args);
        long end = System.currentTimeMillis();
        log.info("代理执行方法后");
        log.info("方法执行时长" + (end - start) + "毫秒");
        return temp;
    }
}
