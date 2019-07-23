package com.xl.base;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-07-23
 * @time 15:41
 * To change this template use File | Settings | File Templates.
 */
public class StringJoinerTest {
    @Test
    void name() {
        StringJoiner sj = new StringJoiner(":", "[", "]");
        print(sj);
        //只加分隔符
        StringJoiner sj2 = new StringJoiner(",");
        print(sj2);
    }
    
    private void print(StringJoiner sj) {
        //可以有重复
        sj.add("George").add("Sally").add("Fred").add("George");
        String desiredString = sj.toString();
        System.out.println(desiredString);
    }
    
    @Test
    void toList() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        System.out.println(numbers.stream().map(i -> i.toString()).collect(Collectors.joining(", ")));
    }
}
