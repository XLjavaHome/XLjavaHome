package com.xl.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriterTest {
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("buf.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("as\r\ndfd");
        bw.newLine();
        for (int x = 1; x < 5; x++) {
            bw.write("abcd" + x);
            bw.newLine();
            bw.flush(); // 写一次刷一次，怕停电！
        }
        bw.flush(); // 只要用到缓冲区，就要刷新
        bw.close(); // 其实关闭缓冲区就是在关闭缓冲区中的流对象
        // fw.close(); //所以这个不用写！!
    }
}
