package com.xl.base;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-06-26
 * @Time: 16:48
 * To change this template use File | Settings | File Templates.
 */
public class JNITest {
    public native void displayHelloWorld();//所有native关键词修饰的都是对本地的声明

    static {
        System.loadLibrary("hello");//载入本地库
    }
    public static void main(String[] args) {
        new JNITest().displayHelloWorld();
    }
}
