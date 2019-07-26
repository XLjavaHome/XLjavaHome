package com.xl.util;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-07-26
 * @time 9:02
 * To change this template use File | Settings | File Templates.
 */
public class StreamUtil {
    /**
     * 自定义去重比较器
     * 例如<p>students.stream().filter(StreamUtil.distinctByKey(Student::getId)).collect(Collectors.toList()).forEach(System
     * .out::println);</p>
     *
     * @param keyExtractor
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        //构造一个去重的线程安全的set集合
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(((Function<? super T, ?>) keyExtractor).apply(t));
    }
}
