package com.xl.internet;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created with 徐立.内部类的测试，不能直接new只能通过这个对象new,Person p = icl,new Person();报错
 *
 * @author: 徐立
 * @Date: 2018-11-04
 * @Time: 23:35
 * To change this template use File | Settings | File Templates.
 */
public class InnerClassTest {
    @Data
    @AllArgsConstructor
    class Person {
        private String name;
        private int age;
    }

    public static void main(String[] args) {
        InnerClassTest icl = new InnerClassTest();
        Person person = icl.new Person("张三", 18);
        System.out.println(person);
    }
}
