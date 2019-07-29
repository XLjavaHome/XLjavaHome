package com.xl.base;

import com.xl.enumsupport.FileEnum;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-07-04
 * @time 10:29
 * To change this template use File | Settings | File Templates.
 */
public class FileTest {
    @Test
    void 换行符测试() {
        System.out.println(FileEnum.fileEnum);
        System.out.println(FileEnum.fileEnum.getFileSeparator());
    }
}
