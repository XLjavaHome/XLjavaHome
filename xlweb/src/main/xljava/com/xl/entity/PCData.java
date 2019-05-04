package com.xl.entity;

/**
 * Created with 徐立.
 *
 * @author: 徐立
 * @Date: 2018-12-04
 * @Time: 23:12
 * To change this template use File | Settings | File Templates.
 */
public class PCData {
    private final int intData;
    
    public PCData(int d) {
        intData = d;
    }
    
    public PCData(String d) {
        intData = Integer.valueOf(d);
    }
    
    public int getData() {
        return intData;
    }
    
    @Override
    public String toString() {
        return "data:" + intData;
    }
}
