package com.xl;

import java.util.function.IntBinaryOperator;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-07-19
 * @time 22:46
 * To change this template use File | Settings | File Templates.
 */
public class IntBinaryOperatorImpl implements IntBinaryOperator {
    @Override
    public int applyAsInt(int left, int right) {
        return Math.max(left, right);
    }
}
