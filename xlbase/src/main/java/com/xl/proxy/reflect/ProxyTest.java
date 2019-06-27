package com.xl.proxy.reflect;

import com.xl.base.design.proxy.MyInvocationHandler;
import com.xl.service.Subject;
import com.xl.service.impl.RealSubject;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with 徐立.如果想要完成动态代理，首先需要定义一个InvocationHandler接口的子类，已完成代理的具体操作。 每个方法都拦截<p/>
 * 方法内部不会执行代理
 *
 * @author 徐立
 * @Date: 2018-11-20
 * @Time: 14:07
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class ProxyTest {
    MyInvocationHandler demo;
    Subject sub;
    
    @Before
    public void before() {
        demo = new MyInvocationHandler();
        //代理不传对象会报错
        sub = (Subject) demo.bind(new RealSubject());
    }
    
    /**
     * 在外层catch没效果
     */
    @Test
    public void 异常测试() {
        try {
            sub.exception();
        } catch (Exception e) {
            log.info("这是异常测试", e);
        }
    }
    
    @Test
    public void test() {
        sub.say("Rollen", 20);
    }
}

