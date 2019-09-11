package com.xl.base.spi;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-09-11
 * @time 23:44
 * To change this template use File | Settings | File Templates.
 */
public class DivisionOperationImpl implements IOperation {
    @Override
    public int operation(int numberA, int numberB) {
        return numberA / numberB;
    }
}
