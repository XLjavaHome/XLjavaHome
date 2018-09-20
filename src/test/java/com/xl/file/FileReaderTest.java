package com.xl.file;

import com.xl.util.FileUtil;
import java.io.FileReader;
import java.io.IOException;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-01-19
 * @Time: 13:44
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class FileReaderTest {
    @Test
    public void demoTest() throws IOException {
        // 创建一个读取流对象和制定名称的文件相关联。要保证文件是已经存在的，如果不存在会发生FileNotFoundException
        FileReader fr = new FileReader(FileUtil.createTempFile("1.txt"));
        char c;
        int ch = 0;
        while ((ch = fr.read()) != -1) {
            log.info((char) ch);
        }
        fr.close();
    }
}
