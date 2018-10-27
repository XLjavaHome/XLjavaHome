package com.xl.reflect;

import com.xl.entity.Person;
import org.junit.Test;

// 定义项目接口
interface Subject {
    String say(String name, int age);
}

/**
 * @author 徐立
 * @Decription 如果想要完成动态代理，首先需要定义一个InvocationHandler接口的子类，已完成代理的具体操作。
 * @date 2014-1-19
 */
public class ProxyTest {
    /**
     * 其实在java中有三种类类加载器。 1）Bootstrap ClassLoader 此加载器采用c++编写，一般开发中很少见。
     * 2）Extension ClassLoader 用来进行扩展类的加载，一般对应的是jre\lib\ext目录中的类
     * 3）AppClassLoader 加载classpath指定的类，是最常用的加载器。同时也是java中默认的加载器。
     */
    @Test
    public void classLoad() {
        Person p = new Person();
        System.out.println("classLoad  " + p.getClass().getClassLoader().getClass().getName());
    }

    @Test
    public void test() {
        MyInvocationHandler demo = new MyInvocationHandler();
        Subject sub = (Subject) demo.bind(new RealSubject());
        String info = sub.say("Rollen", 20);
        System.out.println(info);
    }
}

// 定义真实项目
class RealSubject implements Subject {
    @Override
    public String say(String name, int age) {
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
