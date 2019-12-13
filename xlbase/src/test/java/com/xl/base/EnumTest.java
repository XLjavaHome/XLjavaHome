package com.xl.base;

import com.xl.enumsupport.Sort;
import com.xl.translator.Language;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with 徐立.枚举测试
 *
 * @author 徐立
 * @date 2019-07-12
 * @time 13:59
 * To change this template use File | Settings | File Templates.
 */
public class EnumTest {
    @Test
    void valueOfTest() {
        Language en = Language.valueOf("EN");
        System.out.println(en.getEnglish());
    }
    
    @Test
    void name() {
        //枚举的valueOf可以根据枚举名称转换，但是如果没有该枚举值会报异常
        RequestMethod get = RequestMethod.valueOf("POST");
        System.out.println(get.name());
        System.out.println(RequestMethod.POST.name());
        System.out.println(RequestMethod.POST);
    }
    
    /**
     * 枚举转换
     */
    @Test
    void enumerationConversion() {
        Language[] values = Language.values();
        //枚举的序数从0开始：ordinal
        Stream.of(values).forEach(
                language -> System.out.println(language + ":" + language.getEnglish() + ":" + language.ordinal()));
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
