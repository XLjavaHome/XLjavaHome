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
}
