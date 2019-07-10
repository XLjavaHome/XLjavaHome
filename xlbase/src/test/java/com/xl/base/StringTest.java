package com.xl.base;

import java.util.Arrays;
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
    void splitTest() {
        //    空格是否能去掉,不能去掉
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
