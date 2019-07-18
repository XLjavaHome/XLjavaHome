package com.xl;

import com.xl.entity.Student;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
    private List<Student> students = new ArrayList<>();
    
    public StreamTest() {
        for (int i = 0; i < 10; i++) {
            Student s = new Student();
            s.setId(i);
            s.setSex("x");
            s.setName("姓名" + i);
            s.setAddress("");
            s.setAge(i);
            s.setPhone("");
            students.add(s);
        }
    }
    
    @Test
    void 转换大写() {
        String[] collect = students.stream().map(x -> x.getSex().toUpperCase()).toArray(String[]::new);
        printArray(collect);
    }
    
    private void printArray(Object[] objects) {
        System.out.println(Arrays.toString(objects));
    }
    
    @Test
    void array() {
        // 2. Arrays
        String[] strArray = new String[]{"a", "b", "c"};
        Stream<String> stream = Stream.of(strArray);
        printStream(stream);
        stream = Arrays.stream(strArray);
        printStream(stream);
    }
    
    private void printStream(Stream<String> stream) {
        Object[] objects = stream.toArray();
        printArray(objects);
    }
    
    @Test
    void toArray() {
        //别的类型会报错，类型推到错误
        String[] arr2 = students.stream().map(Student::getName).toArray(String[]::new);
        printArray(arr2);
        String[] objects = students.stream().map(Student::getName).toArray(size -> new String[size]);
        printArray(objects);
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
    
    @Test
    void sort() {
    }
}
