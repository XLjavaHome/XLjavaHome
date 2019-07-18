package com.xl.base;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-10
 * @time 15:42
 * To change this template use File | Settings | File Templates.
 */
public class StringTest {
    @Test
    void intern() {
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);
        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
    }
    
    @Test
    void joinTest() {
        Set<String> set = new LinkedHashSet<>(10);
        set.add("张三");
        set.add("李四");
        //转化数组后合并
        System.out.println(StringUtils.join(set.toArray()));
        set = null;
        //结果是null
        System.out.println(StringUtils.join(set, ","));
    }
    
    @Test
    void splitTest() {
        //空格是否能去掉,不能去掉
        //暂时没有发现能去掉空格的
        String str = "  空格 ,111 ";
        String[] split = StringUtils.split(str, ",");
        printArray(split);
        String[] strings = StringUtils.splitByWholeSeparator(str, ",", 0);
        printArray(strings);
        strings = StringUtils.splitPreserveAllTokens(str, " ,", 0);
        printArray(strings);
    }
    
    private void printArray(String[] split) {
        System.out.println(Arrays.toString(split));
    }
    
    @Test
    void format() {
        //静态方法是没有对象的。
        System.out.println(String.format("", 1));
    }
    
    @Test
    void replace() {
        String str = "你好a你as好adewn你好o323";
        //正则替换要中括号，会把每个字都替换掉
        System.out.println(str.replaceAll("[你好]", ""));
        System.out.println(str.replace("你好", ""));
    }
    
    @Test
    void StringTokenizerTest() {
        //用空格表示
        StringTokenizer tokenizer = new StringTokenizer("developmentTaskartTask", ",!' '.;");
        while (tokenizer.hasMoreTokens()) {
            String str = tokenizer.nextToken();
            System.out.println(str);
        }
    }
}
