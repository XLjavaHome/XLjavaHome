package com.xl.io;

import com.xl.util.FileUtil;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-07-21
 * @time 16:50
 * To change this template use File | Settings | File Templates.
 */
public class fileWriterTest {
    @Test
    void name() throws IOException {
        File tempFile = FileUtil.getTempFile();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile))) {
            bufferedWriter.write("测试换\r\n是缓存");
        }
        FileUtil.open(tempFile);
    }
}
