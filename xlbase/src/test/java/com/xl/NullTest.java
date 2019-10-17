package com.xl;

import com.xl.entity.Person;
import com.xl.entity.Student;
import java.util.Optional;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-10-17
 * @time 23:45
 * To change this template use File | Settings | File Templates.
 */
public class NullTest {
    /**
     * Optional.ofNullable确实可以防止为null
     */
    @Test
    void nullTest() {
        Person son;
        son = null;
        String s = Optional.ofNullable(son).map(Person::getStudent).map(Student::getName).orElse(null);
        System.out.println(s);
        son = new Person();
        Student student = new Student();
        student.setName("张三");
        son.setStudent(student);
        String test = Optional.ofNullable(son).map(Person::getStudent).map(Student::getName).orElse(null);
        System.out.println(test);
    }
}
