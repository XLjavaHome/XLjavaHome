package com.xl;

import com.xl.entity.Student;
import java.util.ArrayList;
import java.util.List;
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
    /**
     * 改变了引用的值 全部都会变
     */
    @Test
    void name() {
        List<Student> listOne = new ArrayList<>();
        listOne.add(new Student("张三", 10));
        listOne.add(new Student("李四", 20));
        List<Student> listTwo = new ArrayList<>();
        listTwo.addAll(listOne);
        Student student = listOne.get(1);
        student.setName("王五");
        listOne.remove(0);
        System.out.println(listOne);
    }
}
