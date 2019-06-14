package com.xl.base;

import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-14
 * @time 18:07
 * To change this template use File | Settings | File Templates.
 */
public class AnnotationTest {
    @Test
    public void name() {
        testParam("测试");
    }
    
    /**
     * 在class文件中都保存了
     *
     * @param s
     */
    @NonNull
    private void testParam(@NotNull String s) {
    }
}
