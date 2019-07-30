package com.xl;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-07-30
 * @time 9:28
 * To change this template use File | Settings | File Templates.
 */
public class IntStreamTest {
    @Test
    void forEach() {
        //无法利用并发的优势
        IntStream.range(0, 1000).forEach(System.out::println);
        IntStream.range(0, 1000).forEachOrdered(System.out::println);
    }
    
    @Test
    void createCollection创建集合() {
        Set<Integer> list = IntStream.range(1, 100).boxed().collect(Collectors.toSet());
        Map<Integer, Integer> map = list.stream().collect(Collectors.toMap(p -> p, q -> q * 3));
        System.out.println(map);
    }
}
