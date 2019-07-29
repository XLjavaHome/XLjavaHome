package com.xl.base;

import com.xl.translator.impl.GoogleTranslator;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-11
 * @time 13:45
 * To change this template use File | Settings | File Templates.
 */
public class MethodTest {
    /**
     * 判断方法是否是公有的
     *
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @Test
    public void name() throws IllegalAccessException, InstantiationException {
        Method[] methods = GoogleTranslator.class.getDeclaredMethods();
        for (Method method : methods) {
            int modifiers = method.getModifiers();
            System.out.println(modifiers);
            System.out.println(Modifier.isPublic(modifiers));
        }
    }
}
