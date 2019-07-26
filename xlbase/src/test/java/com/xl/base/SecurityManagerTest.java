package com.xl.base;

import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-07-25
 * @time 9:23
 * To change this template use File | Settings | File Templates.
 */
public class SecurityManagerTest {
    @Test
    void name() {
        SecurityManager sm = System.getSecurityManager();
        System.out.println(sm);
    }
}
