package com.xl.util;

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
    @Test
    public void getProjectPathTest() {
        System.out.println(FileUtil.getProjectPath());
    }
}
