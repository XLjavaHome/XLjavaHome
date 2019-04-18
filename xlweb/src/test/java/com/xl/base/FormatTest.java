package com.xl.base;

import lombok.extern.log4j.Log4j;
import org.junit.Test;

/**
 * Created with 徐立.占位符测试
 *
 * @author 徐立
 * @date 2019-03-27
 * @time 13:45
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class FormatTest {
    /**
     * 占位符
     */
    @Test
    public void formatTest() {
        //空值测试，显示null值
        System.out.println(String.format("这是%1$s，你好", null));
        //没有效果
        System.out.println(String.format("百分比占位符%%", "30%"));
    }
}
