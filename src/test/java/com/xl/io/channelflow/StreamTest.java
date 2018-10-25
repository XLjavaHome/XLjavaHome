package com.xl.io.channelflow;

import com.xl.entity.Person;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-08-08
 * @Time: 10:01
 * To change this template use File | Settings | File Templates.
 */
public class StreamTest {
    List<Person> list = new ArrayList<>(100);
    
    @Before
    public void before() {
        list.add(new Person("jack", 20));
        list.add(new Person("mike", 25));
        list.add(new Person("tom", 30));
    }
    
    @Test
    public void demoTest() {
        Stream<String> stream = list.stream().map(Person::getName).sorted().limit(10);
        Object[] objects = stream.toArray();
        System.out.println(Arrays.toString(objects));
    }
    
    /**
     * allMatch和anyMatch的区别
     * anyMatch表示，判断的条件里，任意一个元素成功，返回true
     * <p>
     * allMatch表示，判断条件里的元素，所有的都是，返回true
     * <p>
     * noneMatch跟allMatch相反，判断条件里的元素，所有的都不是，返回true
     */
    @Test
    public void allMatchTest() {
        boolean b = list.stream().allMatch(s -> s.getAge() == 1);
        boolean b1 = list.stream().allMatch(s -> s.getAge() == 20);
        System.out.println(b);
        System.out.println(b1);
    }
    
    @Test
    public void anyMatchTest() {
        boolean b = list.stream().anyMatch(s -> s.getAge() == 1);
        boolean b1 = list.stream().anyMatch(s -> s.getAge() == 20);
        System.out.println(b);
        System.out.println(b1);
    }
}
