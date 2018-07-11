package com.xl.io;

import com.xl.util.ResourceUtil;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderTest {
    /**
     * 如果是GBK的会乱码
     *
     * @throws IOException
     */
    @Test
    public void demoTest() throws IOException {
        BufferedReader br = null;
        FileReader fr = new FileReader(ResourceUtil.getResourceFile("utf-8.txt"));
        br = new BufferedReader(fr);
        String s = null;
        while ((s = br.readLine()) != null) { // 当内容为空时结束循环
            System.out.println(s);
        }
        br.close();
    }
}
