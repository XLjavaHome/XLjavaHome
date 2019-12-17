package com.xl;

import com.xl.entity.Person;
import com.xl.entity.Student;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-10-17
 * @time 23:45
 * To change this template use File | Settings | File Templates.
 */
public class OptionalTest {
    /**
     * Optional.of会报空指针异常
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
    
    @Test
    void nullTest2() {
        //直接当三元运算符也可以
        Student s = null;
        s = Optional.ofNullable(s).orElse(new Student());
        System.out.println(s);
    }
    
    @Test
    void emptyTest() {
        Optional<Object> empty = Optional.empty();
        System.out.println(empty);
        Student s = null;
        Optional<Student> studentOptional = Optional.ofNullable(s);
        s = studentOptional.orElse(s);
        System.out.println(s);
        s = studentOptional.orElse(new Student());
        System.out.println(s);
        //orElseGet 为空就会执行
        Integer orElseGet = studentOptional.map(Student::getAge).orElseGet(() -> 1);
        System.out.println(orElseGet);
    }
    
    @Test
    void a() {
        Person son;
        son = null;
        //如果为空就不执行
        Optional.ofNullable(son).ifPresent(son1 -> System.out.println(son1.getName()));
        son = new Person();
        son.setName("张三");
        Optional.ofNullable(son).ifPresent(son1 -> System.out.println(son1.getName()));
    }
    
    @Test
    void flatMapTest() {
        //降维，二维数组缓存1维  ,返回值必须是stream
        Stream<String[]> stream = Stream.of("11,333", "22,", "333,,").map(x -> x.split(","));
        stream.flatMap(strings -> {
            System.out.println(strings.length);
            return Stream.of(strings);
        }).forEach(System.out::println);
    }
}
