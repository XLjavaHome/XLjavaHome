package com.xl.base;

import org.junit.Test;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-02-23
 * @Time: 9:24
 * To change this template use File | Settings | File Templates.
 */
public class SystemTest {
    @Test
    public void ScannerTest() {
        Scanner sc = new Scanner(System.in);
    }

    @Test
    public void soutTest() {
        System.err.println("测试错误。颜色是红色");
    }
}
