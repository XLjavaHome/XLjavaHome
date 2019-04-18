package com.xl.base;

import java.lang.reflect.InvocationTargetException;
import lombok.Data;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-05-24
 * @Time: 17:32
 * To change this template use File | Settings | File Templates.
 */
@Data
public class ObjectTest implements Cloneable {
    private String name;
    private String password;

    /**
     * 此方法用来克隆对象。克隆完成之后会产生一个新的对象，这个新对象和原对象的地址不同但是属性值是一样的。
     * <p>
     * 此方法说的通俗一点，就是一间房子完全仿照另外一间房子建造，不同的只是位置，其他的全部一样。
     * <p>
     * 一个对象要想被克隆，那么这个对象对应的类必须实现Cloneable接口，Cloneable接口中没有任何的方法和属性，仅仅用于标识这个类产生的对象可以被克隆。
     *
     * @throws CloneNotSupportedException
     */
    @Test
    public void cloneTest() throws CloneNotSupportedException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ObjectTest obj = new ObjectTest();
        obj.setName("张三");
        obj.setPassword("11234");
        Object clone = obj.clone();
        System.out.println(obj);
        System.out.println(clone);
        // 用BeanUtils.cloneBean也能实现对象克隆
        Object obj2 = BeanUtils.cloneBean(obj);
        System.out.println(obj2);
    }
}
