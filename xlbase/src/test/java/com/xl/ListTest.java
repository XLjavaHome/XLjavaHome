package com.xl;

import com.xl.entity.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-27
 * @time 17:47
 * To change this template use File | Settings | File Templates.
 */
public class ListTest {
    private List<Student> listOne = new ArrayList<>();
    
    /**
     * 改变了引用的值 全部都会变
     */
    @Test
    void name() {
        createList();
        List<Student> listTwo = new ArrayList<>();
        listTwo.addAll(listOne);
        Student student = listOne.get(1);
        student.setName("王五");
        listOne.remove(0);
        System.out.println(listOne);
    }
    
    @Test
    void 集合转数组() {
        createList();
        Student[] students = listOne.toArray(new Student[listOne.size() - 1]);
        Stream.of(students).forEach(System.out::println);
    }
    
    private void createList() {
        listOne.add(new Student("张三", 10));
        listOne.add(new Student("李四", 20));
        listOne.add(new Student("王五", 30));
    }
    
    @Test
    void spi() throws InterruptedException {
        createList();
        Spliterator<Student> studentSpliterator = listOne.spliterator();
        studentSpliterator.trySplit().forEachRemaining(
                student -> System.out.println(Thread.currentThread().getName() + ":" + student.getAge()));
        Thread.sleep(5000);
    }
    
    /**
     * 循环处理，可以进行各种操作
     */
    @Test
    void forTest() {
        createList();
        listOne.forEach(student -> {
            student.setId(1);
            student.setName("test");
        });
        listOne.forEach(System.out::println);
    }
}
