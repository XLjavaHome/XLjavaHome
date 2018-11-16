package com.xl.reflect;

import lombok.extern.log4j.Log4j;
import org.junit.Test;

// 定义项目接口
interface Subject {
    String say(String name, int age);
}

/**
 * Created with 徐立.如果想要完成动态代理，首先需要定义一个InvocationHandler接口的子类，已完成代理的具体操作，执行代理方法会调用InvocationHandler子类的invoke方法，可以在里面进行一些操作
 *
 * @author: 徐立
 * @Date: 2018-11-16
 * @Time: 15:31
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
    }
}

// 定义真实项目
@Log4j
class RealSubject implements Subject {
    @Override
    public String say(String name, int age) {
        log.info(name + age);
        return name + "  " + age;
    }
}
// 类的生命周期
// 在一个类编译完成之后，下一步就需要开始使用类，如果要使用一个类，肯定离不开JVM。在程序执行中JVM通过装载，链接，初始化这3个步骤完成。
// 类的装载是通过类加载器完成的，加载器将.class文件的二进制文件装入JVM的方法区，并且在堆区创建描述这个类的java.lang.Class对象。用来封装数据。
// 但是同一个类只会被类装载器装载以前
// 链接就是把二进制数据组装为可以运行的状态。
//
// 链接分为校验，准备，解析这3个阶段
// 校验一般用来确认此二进制文件是否适合当前的JVM（版本），
// 准备就是为静态成员分配内存空间，。并设置默认值
// 解析指的是转换常量池中的代码作为直接引用的过程，直到所有的符号引用都可以被运行程序使用（建立完整的对应关系）
// 完成之后，类型也就完成了初始化，初始化之后类的对象就可以正常使用了，直到一个对象不再使用之后，将被垃圾回收。释放空间。
// 当没有任何引用指向Class对象时就会被卸载，结束类的生命周期
