package com.xl.util;

import java.io.File;
import java.io.IOException;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-09-14
 * @Time: 10:03
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class FileUtilTest {
    /**
     timeout junit的超时时间
     * @throws InterruptedException
     */
    @Test
    public void getProjectPathTest() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println(FileUtil.getProjectPath());
    }
    
    @Test
    public void name() throws IOException {
File file=FileUtil.createRandomFile();
        FileUtil.write(file,"测试回车\n\n测试回车");
        FileUtil.open(file);
    }
}
