package com.xl;

import com.xl.entity.Student;
import java.util.*;
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
    private Set<Student> students = new HashSet<>();
    
    public StreamTest() {
        for (int i = 0; i < 100; i++) {
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
    void 无限流() {
        //无限流
        Stream<Integer> stream3 = Stream.iterate(1, (x) -> x + 1).limit(50);
        //stream3.forEach(System.out::println);
        stream3.sorted((o1, o2) -> o2 - o1).forEach(System.out::println);
    }
    
    /**
     * filter后是boolan用于过滤，可以过滤多个条件
     */
    @Test
    void 过滤() {
        students.stream().filter(student -> student.getId() > 10).filter(student -> student.getName().contains("1")).limit(50)
                .forEach(System.out::println);
        //转换list
        List<Student> collect = students.stream().filter(student -> student.getId() > 10)
                                        .filter(student -> student.getName().contains("1")).collect(Collectors.toList());
    }
    
    private void println(String string) {
        System.out.println(string);
    }
    
    /**
     *
     */
    @Test
    void 转换大写() {
        students.stream().map(x -> x.getName()).forEachOrdered(System.out::println);
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
    void demo2() {
        List<Student> studentList = new ArrayList<>(100);
        for (int i = 0; i < 10; i++) {
            Student e = new Student();
            e.setId(i);
            e.setAge(i);
            e.setAddress("地址:" + i);
            studentList.add(e);
        }
        //studentList.stream()
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
        //企业开发 由数据库的高度依赖变为自己的
        //2.stream流
        List<Integer> newList2 = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        System.out.println(newList2);
    }
    
    @Test
    void sort() {
    }
}
