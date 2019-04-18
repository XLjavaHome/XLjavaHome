package com.xl.file;

import com.xl.util.FileUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

/**
 * @author 徐立
 * @Decription 随机文件读取Demo
 * @date 2014-2-22
 */
public class RandomAccessFileTest {
    private File file;

    @Before
    public void before() throws UnsupportedEncodingException {
        file = new File(FileUtil.getCurrentPath(this), "src/src/main/resources/1.txt");
    }

    public void read() throws IOException // 读取
    {
        RandomAccessFile raf = new RandomAccessFile("src/src/main/resources/1.txt", "r"); // 只读的话，可以屏蔽掉只读
        System.out.println(raf.read());
        // Print.print(raf.read()); //取出的数字192
        // 要想读取李四,李四是四个字节，就得建立数组
        byte[] buf = new byte[4];
        raf.read(buf); // 读存buf的长度，并存储到buf数组
        // 转换成字符串
        String name = new String(buf);
        System.out.println("姓名：" + name);
        System.out.println("年龄: " + raf.readInt());
    }

    public void read_2() throws IOException // 读取王五
    {
        // 想要直接读取中间的王五,因为王五在4 李四 15 的后面字节数就是
        RandomAccessFile raf = new RandomAccessFile("src/src/main/resources/1.txt", "r");
        raf.seek(9); // write写入的是一个字节，一个汉字是两个字节，readInt读取4个字节
        byte[] buf = new byte[4];
        raf.read(buf);
        String name = new String(buf);
        System.out.println("姓名：" + name);
    }

    @Test
    public void read_3() throws IOException // skipBytes
    // 跳过指定字节，只能往下跳，不能往回跳
    {
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        int a = raf.skipBytes(3);
        System.out.println(a);
    }

    /**
     * 添加元素,希望在第四个位置添加周七,直接写到指定位置，不覆盖文件
     *
     * @throws IOException
     */
    @Test
    public void write() throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        raf.seek(13);
        raf.write("周七".getBytes());
        raf.writeInt(103);
        raf.close();
    }

    @Test
    public void write2() throws IOException // 写入
    {
        RandomAccessFile raf = new RandomAccessFile(file, "rws");
        raf.write(4); // 只会覆盖文件
        raf.write("李四".getBytes()); // 李四是4个字节
        raf.writeInt(15); // 写入age
        raf.write("王五".getBytes()); // 李四是4个字节
        raf.writeInt(16); // 写入age
    }
}
