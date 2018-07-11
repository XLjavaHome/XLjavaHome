package com.xl.io;

import org.junit.Test;

import java.io.File;

public class FilepathSeparator {
    @Test
    public void filepathSeparatorTest() {
        System.out.println("pathSeparator：" + File.pathSeparator); // 调用静态常量
        System.out.println("pathSeparatorChar" + File.pathSeparatorChar);
        System.out.println("separator：" + File.separator); // 调用静态常量
        System.out.println("separatorChar:" + File.separatorChar);
        System.out.println(";");
    }
}
