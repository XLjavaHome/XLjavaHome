package com.xl.base;

import com.xl.enumsupport.Sort;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.枚举测试
 *
 * @author 徐立
 * @date 2019-07-12
 * @time 13:59
 * To change this template use File | Settings | File Templates.
 */
public class EnumTest {
    @Test
    void 构造方法测试() {
        //保证构造方法只执行一次
        Sort asc = Sort.ASC;
        asc = Sort.ASC;
        asc = Sort.ASC;
        asc = Sort.ASC;
        asc = Sort.ASC;
    }
}
