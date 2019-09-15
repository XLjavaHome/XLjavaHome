package com.xl.test;

import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-09-15
 * @time 16:22
 * To change this template use File | Settings | File Templates.
 */
public class ReflectTest {
    /**
     * 没有找到一个类的子类的方法
     *
     * @throws ClassNotFoundException
     */
    @Test
    void name() throws ClassNotFoundException {
        Class<?> name = Class.forName("com.xl.service.FileService");
        System.out.println(name.getSigners());
    }
}
