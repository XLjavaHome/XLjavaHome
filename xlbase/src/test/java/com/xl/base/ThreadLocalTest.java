package com.xl.base;

import com.xl.entity.Student;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-17
 * @time 16:15
 * To change this template use File | Settings | File Templates.
 */
public class ThreadLocalTest {
    static ThreadLocal<Student> longLocal = new ThreadLocal<>();
    
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            Student student = new Student(" 张三", 18);
            longLocal.set(student);
            System.out.println(get());
        });
        Thread thread2 = new Thread((() -> {
            Student student2 = new Student(" 李四", 20);
            longLocal.set(student2);
            System.out.println(get());
        }));
        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        System.out.println(longLocal.get());
    }
    
    static Student get() {
        return longLocal.get();
    }
}
