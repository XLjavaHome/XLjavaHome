package com.xl;

import com.xl.util.CharacterUtil;
import com.xl.util.FileUtil;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-18
 * @time 23:26
 * To change this template use File | Settings | File Templates.
 */
public class FileTest {
    @Test
    public void name() throws IOException {
        File desktopFile = FileUtil.getDesktopFile("code.txt");
        Charset fileEncode = null;
        fileEncode = CharacterUtil.getURLEncode(desktopFile.toURI().toURL());
        System.out.println(fileEncode.name());
        String s = FileUtils.readFileToString(desktopFile, fileEncode.name());
        System.out.println(s);
    }
}
