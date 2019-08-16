package com.xl.base;

import com.xl.util.FileUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-08-16
 * @time 10:31
 * To change this template use File | Settings | File Templates.
 */
public class ResourceTest {
    @Test
    void name() throws FileNotFoundException, UnsupportedEncodingException {
        FileInputStream fileInputStream = FileUtil.getResourcesFileInputStream("excel\\b.xls");
        System.out.println(fileInputStream);
    }
}
