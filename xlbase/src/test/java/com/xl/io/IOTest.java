package com.xl.io;

import com.xl.util.FileUtil;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-07-02
 * @time 16:38
 * To change this template use File | Settings | File Templates.
 */
public class IOTest {
    @Test
    void name() throws FileNotFoundException {
        File tempFile = FileUtil.getTempFile();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(tempFile));
    }
}
