package com.xl.base;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-05-30
 * @time 23:19
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class TryTest {
    @Test
    public void name() {
        //try括号 会关闭资源不用在finally关闭,并且不能写多个
        try (FileOutputStream fileOutputStream = new FileOutputStream("D:\1")) {
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void testTry() {
        try {
            try {
                int i = 1 / 0;
            } catch (Exception e) {
                //只会打印这个异常
                log.info("这是异常2");
            }
        } catch (Exception e) {
            log.info("你好");
        }
    }
    
    @Test
    public void name2() throws IllegalAccessException, InstantiationException {
        Class c = this.getClass();
        try {
            Method m = c.getMethod("testTry");
            m.invoke(c);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            log.info("bbbb");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            log.info("cccc");
        }
    }
    
    @Test
    public void tryAndTry() {
        try {
            try {
                test();
            } catch (Exception e) {
                //只会打印这个异常
                log.info("这是异常2");
            }
        } catch (Exception e) {
            log.info("你好");
        }
    }
    
    private void test() {
        int i = 1 / 0;
    }
}
