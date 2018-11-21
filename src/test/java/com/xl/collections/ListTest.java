package com.xl.collections;

import com.xl.entity.Student;
import com.xl.util.ListUtil;
import edu.emory.mathcs.backport.java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * ArrayList
 * 底层数据结构是数组。线程不安全
 * LinkedList
 * 底层数据结构是链表。线程不安全
 * Vector
 * 底层数据结构是数组。线程安全
 *
 * @author: 徐立
 * @Date: 2018-01-17
 * @Time: 13:29
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class ListTest {
    List<String> list = new ArrayList<String>();
    List<Student> studentList = new ArrayList<Student>();
    String[] arrays = new String[10];

    @Before
    public void init() {
        list.add("张三");
        list.add("张三");
        list.add("李四");
        list.add(null);
        list.add("1");
        list.add("2");
        System.out.println(list);
        arrays[0] = "张三";
        arrays[1] = "张三";
        for (int i = 0; i < 3; i++) {
            Student s = new Student();
            s.setName("测试" + i);
            studentList.add(s);
        }
        Student s = new Student();
        s.setName("测试0");
        studentList.add(s);
    }

    /**
     * list.iterator() 可以再次循环
     */
    @Test
    public void iteratorTest() {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * 去重
     */
    @Test
    public void distinctTest() {
        System.out.println(list);
        System.out.println(ListUtil.distinct(list));
    }

    @Test
    public void lamdbaTest() {
        //在lamdba表达式中可以用局部变量
        StringBuffer content = new StringBuffer();
        studentList.forEach(s -> {
            content.append(s.getName());
            System.out.println(s);
        });
        System.out.println(content);
    }

    /**
     * 转化为数组后，添加报错
     */
    @Test
    public void arrayTest() {
        List<String> strings = new ArrayList<>();
        strings.addAll(Arrays.asList(arrays));
        ListUtil.distinct(strings);
        List<String> arrays = new ArrayList<>();
        arrays.addAll(strings);
        arrays.add("测试");
        System.out.println(strings);
        System.out.println(arrays);
    }

    @Test
    public void emptyTest() {
        List empty = new ArrayList();
        log.info(empty);
    }

    @Test
    public void sortTest() {
        Collections.sort(list, (o1, o2) -> {
            if (o1 != null && o2 != null) {
                return o1.toString().compareTo(o2.toString());
            }
            return o1 != null ? 1 : -1;
        });
        System.out.println(list);
    }

    @Test
    public void containTest() {
        List<Integer> interlist = new ArrayList<>();
        interlist.add(new Integer(1));
        //true
        System.out.println(interlist.contains(1));
    }
    
    @Test
    public void capitalTest() {
        List list = new ArrayList();
        for (int i = 0; i < 1000000; i++) {
            list.add(i);
        }
    }
    
    @Test
    public void capitalTest2() {
        List list = new ArrayList(1000000);
        for (int i = 0; i < 1000000; i++) {
            list.add(i);
        }
    }
    
}
