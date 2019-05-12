package java.lang;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
;
//package com.xl;

/**
 * Created with 徐立.一个用户自定义的类都是利用扩展加载器的方式进行加载的.但是java.util.Date或者是java.lang.String上是有加载器的,但是java里面为了保证程序的安全,提供了一个概念--双生加载.如果是系统类库,则使用自己的加载器.外部无法控制,而如果用户有需要也可以定义特定的加载,而这两个加载器没有冲突.
 * 目的:为了程序的安全,就算定义了重名的类名称,类也不会加载
 * <p>
 *         
 *
 * @author 徐立
 * @date 2019-05-10
 * @time 22:47
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class ClassLoaderTest {
    @Test
    public void name() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        ClassLoader classLoader1 = Thread.currentThread().getContextClassLoader();
        //一样
        print(classLoader);
        print(classLoader1);
        System.out.println(classLoader == classLoader1);
    }
    
    private void print(ClassLoader classLoader1) {
        System.out.println(classLoader1);
    }
    
    @Test
    public void parentTest() {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        print(contextClassLoader);
        print(contextClassLoader.getParent());
        print(contextClassLoader.getParent().getParent());
    }
}
