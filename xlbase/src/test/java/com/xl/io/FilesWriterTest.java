package com.xl.io;

import com.xl.util.FileUtil;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-08-02
 * @time 23:04
 * To change this template use File | Settings | File Templates.
 */
public class FilesWriterTest {
    @Test
    void name() throws IOException {
        byte[] bytes = "测试".getBytes();
        Path path = Paths.get(FileUtil.getTempFile().getAbsolutePath());
        Path write = Files.write(path, bytes);
        FileUtil.open(new File(write.toString()));
    }
}
