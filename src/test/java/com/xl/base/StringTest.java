package com.xl.base;

import com.xl.util.StringUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA. String类不可被继承
 * User: 徐立
 * Date: 2017/8/28
 * Time: 9:59
 * To change this template use File | Settings | File Templates.
 */
public class StringTest {
    String birthday = "111111111111111119900228";
    StringBuffer sql = new StringBuffer();

    @Before
    public void before() {
        sql.append("12e13,3232,aa");
    }

    /**
     * 是否匹配给定的正则
     */
    @Test
    public void matchTest() {
        System.out.println(birthday.matches("\\d{4,}"));
    }

    /**
     * 转换为join字符串
     */
    @Test
    public void joinTest() {
        List<String> result = new ArrayList<String>();
        result.add("aaa");
        result.add("bbb");
        result.add("ccc");
        result.add("ddd");
        String s = StringUtil.join(result, ",");
        System.out.println(s);
    }

    @Test
    public void spiltTest() {
        String s = "测试";
        System.out.println(Arrays.toString(s.split(",")));
    }

    @Test
    public void bufferAndBuilderTest() {
        StringBuilder sb = new StringBuilder();
        StringBuffer sb2 = new StringBuffer();
    }

    /**
     * 替换所有的字符串
     * replaceAll可以正则
     */
    @Test
    public void replaceTest() {
        System.out.println("111.3333.444".replace(".", "测试"));
    }

    @Test
    public void deleteTest() {
        System.out.println(sql);
        sql.delete(sql.length() - 2, sql.length());
        System.out.println(sql);
    }

    @Test
    public void insertTest() {
        sql.insert(0, "测试插入");
    }

    @Test
    public void equalTest() {
        String s1 = "Hollis";
        String s2 = new String("Hollis");
        String s3 = new String("Hollis").intern();
        //false
        System.out.println(s1 == s2);
        //true
        System.out.println(s1 == s3);
    }

    @After
    public void after() {
        System.out.println(sql);
    }
}
