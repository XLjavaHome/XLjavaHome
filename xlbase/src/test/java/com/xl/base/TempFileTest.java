package com.xl.base;

import com.xl.util.SystemUtil;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-07-17
 * @time 18:30
 * To change this template use File | Settings | File Templates.
 */
public class TempFileTest {
    @Test
    void name() {
        System.out.println(System.getProperty(SystemUtil.JAVA_IO_TMPDIR));
    }
}
