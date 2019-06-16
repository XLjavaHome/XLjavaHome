package com.xl;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-16
 * @time 21:45
 * To change this template use File | Settings | File Templates.
 */
public class AssertTest {
    @Test
    public void name() {
        Object[] locations = null;
        //有一个为空就报异常
        Assert.noNullElements(locations, "Config locations must not be null");
        locations = new Object[]{"11", null};
        Assert.noNullElements(locations, "Config locations must not be null");
    }
}
