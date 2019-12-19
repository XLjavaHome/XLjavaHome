package com.xl;

import org.junit.jupiter.api.Test;
import org.springframework.util.ClassUtils;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-12-19
 * @time 22:17
 * To change this template use File | Settings | File Templates.
 */
public class TypeTest {
    @Test
    void name() {
        System.out.println(ClassUtils.isAssignable(Number.class, Integer.class));
    }
}
