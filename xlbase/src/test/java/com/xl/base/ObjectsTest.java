package com.xl.base;

import java.util.Objects;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-05-12
 * @time 23:01
 * To change this template use File | Settings | File Templates.
 */
public class ObjectsTest {
    @Test
    public void name() {
        String s = "1212";
        //如果为空直接报异常
        System.out.println(Objects.requireNonNull(s));
    }
    
    @Test
    public void getComponentType() {
        String s = "测试";
        Class<?> componentType = s.getClass().getComponentType();
        System.out.println(componentType);
    }
}
