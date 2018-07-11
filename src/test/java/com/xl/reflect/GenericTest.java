package com.xl.reflect;

import com.xl.entity.Student;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.泛型
 *
 * @author: 徐立
 * @Date: 2018-04-13
 * @Time: 13:12
 * To change this template use File | Settings | File Templates.
 */
public class GenericTest {
    @Test
    public void getByidTest() throws InstantiationException, IllegalAccessException {
        Student st = getByid(Student.class);
        System.out.println(st);
    }

    public <T> T getByid(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }
}
