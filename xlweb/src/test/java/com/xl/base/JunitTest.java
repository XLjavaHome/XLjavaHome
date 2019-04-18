package com.xl.base;

import com.xl.entity.Person;
import lombok.extern.log4j.Log4j;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

@Log4j
public class JunitTest {
    // 使用Junit测试加@Test就可以 要直接测试所有的方法就点击类名，然后run as
    private Person p;

    // 这个要比Before还有先加载，类加载的时候运行
    @BeforeClass
    public static void beforeClass() {
        log.info("beforeClass");
    }

    @AfterClass
    public static void aflterClass() {
        log.info("AfterClass");
    }

    // Before是在测试之前必定运行，所以可以把初始化Person交给Before
    // 特点：每个测试方法都运行
    @Before
    public void before() {
        log.info("before");
        p = new Person();
    }

    @Test
    public void testRun() {
        // Person p = new Person();
        // 断言.里面很多方法
        // 一个期望值，一个返回值
        Assert.assertEquals("2", "2");
        // 期望返回的数组是1，1. 实际的返回的数组是1，2
        //Assert.assertArrayEquals(new int[]{1, 1}, new int[]{1, 2});
    }

    // After可以用来关闭资源
    @After
    public void after() {
        log.info("after");
    }
}
