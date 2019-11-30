package com.xl;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-11-30
 * @time 21:45
 * To change this template use File | Settings | File Templates.
 */
public class ExceptionTest {
    @Test
    void test1() {
        int i = intAdd();
        System.out.println(i);
    }
    
    private int intAdd() {
        int i = 0;
        try {
            i++;
            System.out.println("try block, i = " + i);
            return i;
        } catch (Exception e) { //NumberFormatException
            i++;
            System.out.println("catch block i = " + i);
            return i;
        } finally {
            i++;
            System.out.println("finally block i = " + i);
            //返回跟不返回有区别
            return i;
        }
    }
    
    @Test
    void testCollection() {
        //finally会执行所以会加入到list集合
        List<String> list = collectionTest();
        System.out.println(list);
    }
    
    private List<String> collectionTest() {
        List<String> list = new ArrayList<>();
        try {
            list.add("step try");
            System.out.println("try block");
            return list;
        } catch (Exception e) {
            list.add("step catch");
            System.out.println("catch block");
            return list;
        } finally {
            list.add("step finally");
            System.out.println("finally block ");
        }
    }
}
