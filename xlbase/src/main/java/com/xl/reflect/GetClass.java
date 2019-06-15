package com.xl.reflect;

import com.xl.entity.Person;
import org.junit.Test;

public class GetClass {
    // 1.获取字节码对象的方式:
    @Test
    public void getClassObject_1() throws ClassNotFoundException {
        Person p = new Person();
    }

    // 方式二：
    // 2.任何数据类型都具备一个静态的属性.class来获取对应的Class对象。
    // 特点：相对简单，但是还是要明确用到类中的静态成员。还是不够扩展
    @Test
    public void getClassObject_2() {
        Class clazz = Person.class;
        Class clazz1 = Person.class;
        Class clazz2 = new Person().getClass();
        ClassLoaderTest.print(clazz);
        ClassLoaderTest.print(clazz2);
        System.out.println(clazz == clazz1);
    }

    // 方式三：
    // 只要通过给定类的字符串名称就可以获取该类，更为扩展。
    // 可以用Class类中的方法完成。 该方法就是forName
    @Test
    public void getClassObject_3() throws ClassNotFoundException {
        String className = "java.lang.Class"; // 必须带包名
        String className1 = "reflect.GetClass"; // 这样才对
        String className2 = "Person"; // 这样是错误的
        Class clazz = Class.forName(className);
        ClassLoaderTest.print(clazz);
    }
}
