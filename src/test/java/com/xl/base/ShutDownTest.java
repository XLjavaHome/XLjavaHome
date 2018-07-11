package com.xl.base;

import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

public class ShutDownTest {
    @Test
    public void demoTest() throws IOException {
        Runtime ce = Runtime.getRuntime();
        ce.exec("Shutdown.exe -s -t 600"); //设置自动关机还剩下多长时间
        System.out.print("是否取消y/n");
        Scanner sr = new Scanner(System.in);
        String n = sr.next();
        if ("y".equals(n)) {
            ce.exec("shutdown -a");
        }
        System.out.println(ce.exec("shutdown -i"));
    }
}
