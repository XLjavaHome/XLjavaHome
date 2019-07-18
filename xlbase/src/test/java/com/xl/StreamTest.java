package com.xl;

import com.xl.entity.Student;
import java.util.*;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-27
 * @time 17:40
 * To change this template use File | Settings | File Templates.
 */
public class StreamTest {
    @Test
    void array() {
        Set<Student> list = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            Student s = new Student();
            s.setId(i);
            s.setSex("");
            s.setName("姓名" + i);
            s.setAddress("");
            s.setAge(i);
            s.setPhone("");
            list.add(s);
        }
        String[] arr2 = list.stream().map(Student::getName).toArray(String[]::new);
        printArray(arr2);
        String[] objects = list.stream().map(Student::getName).toArray(size -> new String[size]);
        printArray(objects);
    }
    
    private void printArray(String[] objects) {
        System.out.println(Arrays.toString(objects));
    }
    
    @Test
    void name() {
        //取出偶数
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        //1.for循环
        List<Integer> newList1 = new ArrayList<>();
        for (Integer integer : list) {
            if (integer % 2 == 0) {
                newList1.add(integer);
            }
        }
        System.out.println(newList1);
        //2.stream流
        List<Integer> newList2 = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        System.out.println(newList2);
    }
}
