package com.xl.base;

import lombok.Data;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author: 徐立
 * @Date: 2018-10-16
 * @Time: 15:00
 * To change this template use File | Settings | File Templates.
 */
@Data
public class CloneTest implements Cloneable {
    private Integer age;
    
    @Test
    public void demoTest() throws CloneNotSupportedException {
        CloneTest demo1 = new CloneTest();
        demo1.setAge(11);
        System.out.println(demo1.getAge());
        CloneTest clone = (CloneTest) demo1.clone();
        System.out.println(clone);
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
