package com.xl.proxy.reflect;

import java.lang.reflect.Method;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-27
 * @time 23:00
 * To change this template use File | Settings | File Templates.
 */
interface LoginService {
    public boolean checkUser();
}

interface UserService {
    public String getUserName();
}

@Data
@Slf4j
class Student {
    String name;
    private int age;
    
    public void say() {
        log.info("这是say方法");
    }
}

class LoginServiceImpl implements LoginService {
    @Override
    public boolean checkUser() {
        System.out.println("LoginServiceImpl  checkUser");
        return false;
    }
}

class UserServiceImpl implements UserService {
    @Override
    public String getUserName() {
        System.out.println("UserServiceImpl getUserName");
        return null;
    }
}

class CglibProxy implements MethodInterceptor {
    private Log logger = LogFactory.getLog(CglibProxy.class);
    
    @Override
    public Object intercept(Object proxy, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
        logger.info("*********代理方法执行前************");
        Object retObj = methodProxy.invokeSuper(proxy, params);
        logger.info("*********代理方法执行后************");
        return retObj;
    }
    
    //返回目标对象的代理对象
    public Object newProxy(Object target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        enhancer.setClassLoader(target.getClass().getClassLoader());
        return enhancer.create();
    }
}

public class ProxyTestCglib {
    public static void main(String[] args) {
        //创建目标对象
        LoginService loninService = new LoginServiceImpl();
        UserService userService = new UserServiceImpl();
        CglibProxy proxy = new CglibProxy();
        Student student = new Student();
        Object o = proxy.newProxy(student);
        System.out.println(o);
        //创建代理对象
        LoginService loninService$Proxy = (LoginService) proxy.newProxy(loninService);
        UserService userService$Proxy = (UserService) proxy.newProxy(userService);
        loninService$Proxy.checkUser();
        userService$Proxy.getUserName();
    }
    
    @Test
    public void testProxy() {
        CglibProxy proxy = new CglibProxy();
        Student student = new Student();
        Student o = (Student) proxy.newProxy(student);
        o.say();
    }
}

