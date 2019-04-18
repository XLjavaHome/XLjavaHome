package com.xl.file;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo {
    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
        // writeFile();
        writeFile_2();
        readFile();
    }

    public static void writeFile_2() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("ran.txt", "rw");
        raf.write("周期".getBytes()); // 不覆盖文件，直接在文件里面写数据，这是与输出流的区别
        raf.writeInt(103);
        raf.close();
    }

    public static void readFile() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("ran.txt", "r"); // r只读
        // 调整对象中的指针
        // raf.seek(8); //取下一个汉字
        // 跳过指定的字节数
        // raf.skipBytes(8); //遗憾的是不能往回跳
        byte[] buf = new byte[4]; // 因为int 是4个字节
        raf.read(buf);
        String name = new String(buf);// 将字符数组转换为字符串
        System.out.println("name= " + name); // 取出年龄
        int age = raf.readInt();
        System.out.println("age=" + age);
        raf.close();
    }

    public static void writeFile() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("ran.txt", "rw"); // rw读写模式
        raf.write("李四".getBytes());
        // raf.write(97); // 在文本里面显示的是a write方法只写int的最低8位 查阅的是GBK的表
        // raf.write(258); // 数据丢失
        raf.writeInt(38); // 因此出现了这个方法
        raf.write("王五".getBytes());
        raf.writeInt(23);
        raf.close();
    }
}
