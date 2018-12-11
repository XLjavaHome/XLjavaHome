package com.xl.base;

import com.xl.util.StringUtil;
import java.text.MessageFormat;
import java.util.Arrays;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang.StringEscapeUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA. String类不可被继承
 * User: 徐立
 * Date: 2017/8/28
 * Time: 9:59
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class StringTest {
    String birthday = "111111111111111119900228";
    StringBuffer sql = new StringBuffer();
    
    @Before
    public void before() {
        sql.append("12e13,3232,aa");
    }
    
    /**
     * todo 转义
     */
    @Test
    public void transTest() {
        String s = "\\u79fb\\u52a8\\u4e92\\u8054\\u7f51\\u5e94\\u7528";
        String s2 = StringEscapeUtils.unescapeJava(s);
        System.out.println(s);
        System.out.println(s2);
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
        String[] s = new String[]{"11", "", "22"};
        //有空格
        System.out.println(StringUtil.join(s, ","));
    }
    
    @Test
    public void indexTest() {
        String s = "133.测试~~";
        int i = s.indexOf(".");
        String result = s.substring(0, i);
        System.out.println(result);
    }
    
    @Test
    public void spiltTest() {
        String s = "测试21321\n" + "'e234";
        System.out.println(Arrays.toString(s.split(",")));
    }
    
    @Test
    public void bufferAndBuilderTest() {
        StringBuilder sb = new StringBuilder();
        StringBuffer sb2 = new StringBuffer();
    }
    
    /**
     * 替换所有的字符串
     * replace 不使用正则
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
    
    /**
     * d数字，s字符串
     * MessageFormat 用%s占位是不行的
     */
    @Test
    public void placeHolderTest() {
        //二位小数,并且四舍五入
        String test = String.format("%.2f", 123.32621D);
        System.out.println(test);
        //按顺序占位，多一个%s会报错
        String s = String.format("测试%s占位符,%s", "hello world", "这是第二个");
        log.info(s);
        //数字占位符，按照顺序占位 从1开始
        String str = String.format("格式参数$的使用： %1$d,%3$s,%2$s,%2$s，%2$s", 99, "abc", "这是第三个占位符");
        log.info(str);
        //从0开始也可以 第二个是从2开始，如果是3会报错。
        String str2 = String.format("格式参数$的使用： %0$s %2$s", "hello", "world");
        log.info(str2);
        //    占位符中的double类型
        double num = 123.4567899;
        System.out.print(String.format("%f %n", num)); // 123.456790
        System.out.print(String.format("%a %n", num)); // 0x1.edd3c0bb46929p6
        System.out.print(String.format("%g %n", num)); // 123.457
        double num2 = 3;
        System.out.print(String.format("%s", num2)); // 3.0
    }
    
    @Test
    public void MessageFormatTest() {
        //参数可以多没事
        String[] holder = {"hello", "测试"};
        String t2 = MessageFormat.format("MessageFormat占位符() {0},{1},", holder);
        System.out.println(t2);
    }
}
