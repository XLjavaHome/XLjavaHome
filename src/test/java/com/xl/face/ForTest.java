package com.xl.face;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.System.currentTimeMillis1970年1月1号0时0分0秒所差的毫秒数。<br/>
 * java中获取微秒不准确
 * s（秒）、ms（毫秒）、μs（微秒）、ns（纳秒），
 * 其中：1s=1000ms，1 ms=1000μs，1μs=1000ns
 *
 * @author: 徐立
 * @Date: 2018-05-02
 * @Time: 10:34
 * To change this template use File | Settings | File Templates.
 */
public class ForTest {
    /**
     * 1万次循环做判断是0毫秒
     * 10万次循环做判断是2毫秒
     */
    @Test
    public void forTimeTest() {
        ArrayList<Integer> arrayList = new ArrayList<>(20000000);
        for (int i = 0; i < 29; i++) {
            arrayList.add(i);
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = new Date();
        for (Integer integer : arrayList) {
            ;
        }
        Date d2 = new Date();
        System.out.println("for循环时间： " + (d2.getTime() - d1.getTime()) + "ms");
        arrayList.stream().filter(i -> i > 0);
        arrayList.stream().filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                System.out.println(integer);
                return false;
            }
        });
        Date d3 = new Date();
        System.out.println("流循环时间： " + (d3.getTime() - d2.getTime()) + "ms");
        for (Integer i : arrayList) {
            i++;
        }
        Date d4 = new Date();
        System.out.println("强for循环时间： " + (d4.getTime() - d3.getTime()) + "ms");
    }

    @Test
    public void streamTest() {
        String[] s = {"aaa", "bbb", "cccbbb"};
        List<String> stringList = Arrays.asList(s);
        stringList.stream().forEach(e -> System.out.println(e));
        Optional<String> optional = stringList.stream().filter(s1 -> s1.contains("bb")).findFirst();
        System.out.println(optional);
    }

    /**
     * 不同于 for，range 不会强迫我们初始化某个可变变量。
     * 迭代会自动执行，所以我们不需要像循环索引一样定义增量。
     */
    @Test
    public void stream2Test() {
        IntStream.range(1, 5).forEach(i -> System.out.print(i + "..."));
    }
}

