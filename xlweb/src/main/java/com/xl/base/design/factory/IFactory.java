package com.xl.base.design.factory;

/**
 * Created with 徐立.
 *
 * @author: 徐立
 * @Date: 2018-11-18
 * @Time: 15:04
 * To change this template use File | Settings | File Templates.
 */
public interface IFactory {
    //创建产品工厂方法
    <T extends Iproduct> T creatProduct(Class<T> clz);
}
