package com.xl.base;

import com.xl.util.NumberTool;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-03
 * @time 9:18
 * To change this template use File | Settings | File Templates.
 */
public class NumberTest {
    /**
     * 说两个值相 异结果为真。相同为假
     */
    @Test
    public void 异或() {
        //a=15的二进制是1111，b=2的二进制是0010，经过异或运算后，得到的二进制数为1101，转换为十进制就是13。
        int a = 15;
        int b = 2;
        System.out.println("a^b=" + (a ^ b));
    }
    
    @Test
    public void 可变参数测试() {
        System.out.println(NumberTool.safeAdd(1D, 3D));
    }
}
