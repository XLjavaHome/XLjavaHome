package com.xl.reflect.generic;

import com.xl.entity.Person;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.junit.Test;

public class GenericDemo extends A<Person, String> {
    @Test
    public void getGeneric() {
        // 得到泛型的真实信息
        Class<? extends GenericDemo> clazz = this.getClass(); // 得到当前new的对象
        System.out.println(clazz.getName());
        Type[] parent = clazz.getGenericInterfaces();
        System.out.println(parent[0]);
        Class<?> inter = clazz.getInterfaces()[0];
        System.out.println(inter.getName());
        // ParameterizedType pt = (ParameterizedType)
        // clazz.getGenericSuperclass(); // 得到泛型的父类
        // Print.print(pt);
        // clazz = (Class) pt.getActualTypeArguments()[0];
        // Print.print(clazz);
    }

    /**
     * 有些无法new的对象是会出异常的
     */
    @Test
    public void test() {
        new GenericDemo().run();
    }
}

class A<T, E> {
    T entity;
    E domain;

    @SuppressWarnings("unchecked")
    public A() {
        java.lang.reflect.Type t = this.getClass().getGenericSuperclass();
        boolean b = t instanceof ParameterizedType;
        System.out.println("是否是这个类型的" + b);
        ParameterizedType pt = (ParameterizedType) t;
        pt.getRawType();
        pt.getOwnerType();
        java.lang.reflect.Type[] clazzs = pt.getActualTypeArguments();
        try {
            entity = (T) ((Class<?>) clazzs[0]).newInstance();
            domain = (E) ((Class<?>) clazzs[1]).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    void run() {
        System.out.println(entity.getClass().getName());
        System.out.println(domain.getClass().getName());
    }
}
