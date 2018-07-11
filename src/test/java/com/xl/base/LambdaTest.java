package com.xl.base;

import com.xl.entity.Student;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-05-16
 * @Time: 9:41
 * To change this template use File | Settings | File Templates.
 */
public class LambdaTest {
    List<Student> studentList = new ArrayList<Student>();
    String[] players = {"Rafael Nadal", "Novak Djokovic", "Stanislas Wawrinka", "David Ferrer", "Roger Federer", "Andy Murray", "Tomas Berdych", "Juan Martin Del Potro", "Richard Gasquet", "John Isner"};

    @Before
    public void before() {
        for (int i = 0; i < 3; i++) {
            Student s = new Student();
            s.setName("测试" + i);
            studentList.add(s);
        }
    }

    @Test
    public void demoTest() {
        // 使用 lambda 表达式以及函数操作(functional operation)
        studentList.forEach((player) -> System.out.print(player + "; "));
        // 在 Java 8 中使用双冒号操作符(double colon operator)
        studentList.forEach(System.out::println);
        // 使用匿名内部类
        Button btn = new Button();
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hello World!");
            }
        });
        // 或者使用 lambda expression
        btn.addActionListener(event -> System.out.println("Hello World!"));
    }

    @Test
    public void runnableTest() {
        // 1.1使用匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world !");
            }
        }).start();
        // 1.2使用 lambda expression
        new Thread(() -> System.out.println("Hello world !")).start();
        // 2.1使用匿名内部类
        Runnable race1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world !");
            }
        };
        // 2.2使用 lambda expression
        Runnable race2 = () -> System.out.println("Hello world !");
        // 直接调用 run 方法(没开新线程哦!)
        race1.run();
        race2.run();
    }

    @Test
    public void sortTest() {
        // 1.1 使用匿名内部类根据 name 排序 players
        Arrays.sort(players, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1.compareTo(s2));
            }
        });
        // 1.2 使用 lambda expression 排序 players
        Comparator<String> sortByName = (String s1, String s2) -> (s1.compareTo(s2));
        Arrays.sort(players, sortByName);
        // 1.3 也可以采用如下形式:
        Arrays.sort(players, (String s1, String s2) -> (s1.compareTo(s2)));
    }

    @Test
    public void compareTest() {
        // 1.1 使用匿名内部类根据 surname 排序 players
        Arrays.sort(players, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1.substring(s1.indexOf(" ")).compareTo(s2.substring(s2.indexOf(" "))));
            }
        });
        // 1.2 使用 lambda expression 排序,根据 surname
        Comparator<String> sortBySurname = (String s1, String s2) -> (s1.substring(s1.indexOf(" ")).compareTo(s2.substring(s2.indexOf(" "))));
        Arrays.sort(players, sortBySurname);
        // 1.3 或者这样,怀疑原作者是不是想错了,括号好多...
        Arrays.sort(players, (String s1, String s2) -> (s1.substring(s1.indexOf(" ")).compareTo(s2.substring(s2.indexOf(" ")))));
        // 2.1 使用匿名内部类根据 name lenght 排序 players
        Arrays.sort(players, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1.length() - s2.length());
            }
        });
        // 2.2 使用 lambda expression 排序,根据 name lenght
        Comparator<String> sortByNameLenght = (String s1, String s2) -> (s1.length() - s2.length());
        Arrays.sort(players, sortByNameLenght);
        // 2.3 or this
        Arrays.sort(players, (String s1, String s2) -> (s1.length() - s2.length()));
        // 3.1 使用匿名内部类排序 players, 根据最后一个字母
        Arrays.sort(players, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1.charAt(s1.length() - 1) - s2.charAt(s2.length() - 1));
            }
        });
        // 3.2 使用 lambda expression 排序,根据最后一个字母
        Comparator<String> sortByLastLetter = (String s1, String s2) -> (s1.charAt(s1.length() - 1) - s2.charAt(s2.length() - 1));
        Arrays.sort(players, sortByLastLetter);
        // 3.3 or this
        Arrays.sort(players, (String s1, String s2) -> (s1.charAt(s1.length() - 1) - s2.charAt(s2.length() - 1)));
    }
}
