package com.xl.reflect;

import com.xl.service.Subject;
import com.xl.service.impl.RealSubject;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

/**
 * Created with 徐立.如果想要完成动态代理，首先需要定义一个InvocationHandler接口的子类，已完成代理的具体操作。 每个方法都拦截
 *
 * @author: 徐立
 * @Date: 2018-11-20
 * @Time: 14:07
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class ProxyTest {
    @Test
    public void test() {
        MyInvocationHandler demo = new MyInvocationHandler();
        //代理不传对象会报错
        Subject sub = (Subject) demo.bind(new RealSubject());
        sub.say("Rollen", 20);
        sub.eat();
    }
}

 