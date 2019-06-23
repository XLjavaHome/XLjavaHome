package com.xl;

import com.xl.util.CharacterUtil;
import com.xl.util.FileUtil;
import java.io.File;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-19
 * @time 10:01
 * To change this template use File | Settings | File Templates.
 */
public class FileEncoderTest {
    @Test
    public void name() throws MalformedURLException {
        File desktopFile = FileUtil.getDesktopFile("20190611180849517.doc");
        Charset fileEncode = null;
        try {
            fileEncode = CharacterUtil.getURLEncode(desktopFile.toURI().toURL());
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        System.out.println(fileEncode);
    }
}
