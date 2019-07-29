package com.xl.base;

import com.xl.enumsupport.Sort;
import com.xl.translator.Language;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.枚举测试
 *
 * @author 徐立
 * @date 2019-07-12
 * @time 13:59
 * To change this template use File | Settings | File Templates.
 */
public class EnumTest {
    /**
     * 枚举转换
     */
    @Test
    void enumerationConversion() {
        Language[] values = Language.values();
        Stream.of(values).forEach(System.out::println);
        Stream.of(values).forEach(language -> System.out.println(language + ":" + language.getEnglish()));
        System.out.println(values);
        Map<String, String> collect = Stream.of(values).collect(Collectors.toMap(o -> o.toString(), o -> o.getEnglish()));
        collect.forEach((s, s2) -> System.out.println(s + "-" + s2));
    }
    
    @Test
    void 构造方法测试() {
        //保证构造方法只执行一次
        Sort asc = Sort.ASC;
        asc = Sort.ASC;
        asc = Sort.ASC;
        asc = Sort.ASC;
        asc = Sort.ASC;
    }
}
