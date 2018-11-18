package com.xl.reflect;

import com.xl.service.Subject;
import com.xl.service.impl.RealSubject;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

/**
 * @author 徐立
 * @Decription 如果想要完成动态代理，首先需要定义一个InvocationHandler接口的子类，已完成代理的具体操作。
 * @date 2014-1-19
 */
@Log4j
public class ProxyTest {
    @Test
    public void test() {
        MyInvocationHandler demo = new MyInvocationHandler();
        Subject sub = (Subject) demo.bind(new RealSubject());
        sub.say("Rollen", 20);
    }
}

 