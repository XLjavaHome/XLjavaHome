package com.xl.base.design.factory;

import lombok.extern.log4j.Log4j;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @Date: 2018-11-18
 * @Time: 15:03
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class ProductA implements Iproduct {
    @Override
    public void doSomething() {
        log.info("我是产品A，我可以搞事情");
    }
}
