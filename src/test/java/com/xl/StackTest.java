package com.xl;

import java.util.Stack;
import org.junit.Test;

/**
 * Created with 徐立. 先进先出
 *
 * @author: 徐立
 * @Date: 2018-12-01
 * @Time: 22:55
 * To change this template use File | Settings | File Templates.
 */
public class StackTest {
    /**
     * Stack测试
     */
    @Test
    public void StackTest() {
        Stack<Integer> stk = new Stack();
        Integer[] months = {1, 2, 3};
        for (int i = 0; i < months.length; i++) {
            stk.push(months[i]);
        }
        System.out.println("stk = " + stk);
        System.out.println("弹出 elements:");
        while (!stk.empty())
            //public pop () 移除栈顶对象，并作为函数的值 返回该对象。
            System.out.println(stk.pop());
    }
}
