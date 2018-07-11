package com.xl.io.channelflow;

import com.xl.io.ByteArrayStream.Messages;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * 操作文件要调用底层资源，该流对象不调用底层资源，关闭流无效，关闭后仍可以调用,不会产生任何IO异常
 * 好处：自动增长缓冲区,以前建立都是都是1024的整数倍byte数组
 * 目的：内部封装了可变长度的byte数组
 * 特点：在构造的时候，需要接受数据源，而且数据源是一个字节数组
 * ByteArrayOutputStream： 在构造的时候不需要定义,内部封装了可变长度的byte数组。这就是数据目的地
 * 源设备:
 * 键盘 System.in.  硬盘 FileStreamTest  内存 ArrayStream
 * 目的设备：
 * 控制台 System.out 硬盘 FileStreamTest 内存 ArrayStream
 */
public class ByteArrayStreamTest {
    @Test
    public void byteArrayStreamTest() {
        // 数据源。
        ByteArrayInputStream bis = new ByteArrayInputStream(Messages.getString("张三1").getBytes());
        // 数据目的
        ByteArrayOutputStream bos = new ByteArrayOutputStream();// 不指定目的。
        System.out.println("没有数据:" + bos.size());
        // 现在可以频繁的读写
        int by1 = 0;
        while ((by1 = bis.read()) != -1) {
            System.out.println(by1);
            bos.write(by1);
        }
        System.out.println("输出流size:" + bos.size());
        System.out.println(bos.toString()); // abc
    }
}
