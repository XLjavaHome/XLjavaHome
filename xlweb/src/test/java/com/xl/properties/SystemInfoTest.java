package com.xl.properties;

import com.xl.util.FileUtil;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import org.junit.After;
import org.junit.Test;

public class SystemInfoTest {
    private Properties pro = System.getProperties(); // 系
    private File file;

    @Test
    public void demoTest() throws IOException {
        System.out.println(pro);
        //在项目目录下生成
        file = FileUtil.createTempFile("生成一个txt.txt");
        pro.list(new PrintStream(file));
    }

    @After
    public void after() throws IOException {
        FileUtil.open(file);
    }
}
