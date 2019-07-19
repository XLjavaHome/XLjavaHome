package com.xl;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-07-19
 * @time 13:36
 * To change this template use File | Settings | File Templates.
 */
public class CollectionsTest {
    @Test
    void name() {
        //添加会报错
        List<Object> list = Collections.emptyList();
        System.out.println(list);
    }
}
