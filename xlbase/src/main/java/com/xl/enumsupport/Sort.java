package com.xl.enumsupport;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @Date: 2019-01-05
 * @Time: 22:40
 * To change this template use File | Settings | File Templates.
 */
public enum Sort {ASC, DESC;
    
    Sort() {
        //构造方法保证每个实例只执行一次
        System.out.println("这是构造方法");
    }}
