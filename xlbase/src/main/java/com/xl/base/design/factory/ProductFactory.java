package com.xl.base.design.factory;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @Date: 2018-11-18
 * @Time: 15:04
 * To change this template use File | Settings | File Templates.
 */
public class ProductFactory implements IFactory {
    @Override
    public <T extends Iproduct> T creatProduct(Class<T> clz) {
        Iproduct product = null;
        try {
            product = clz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return (T) product;
    }
}







