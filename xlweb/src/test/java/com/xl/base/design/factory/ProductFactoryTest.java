package com.xl.base.design.factory;

import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author: 徐立
 * @Date: 2018-11-18
 * @Time: 15:26
 * To change this template use File | Settings | File Templates.
 */
public class ProductFactoryTest {
    /**
     * 测试工厂类
     */
    @Test
    public void demoTest() {
        IFactory factory = new ProductFactory();
        //只能传Product的子类
        Iproduct product = factory.creatProduct(ProductA.class);
        product.doSomething();
    }
}