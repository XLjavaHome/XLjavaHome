package com.xl.base;

import java.text.MessageFormat;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-03-31
 * @time 22:38
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class MessageFormfatTest {
    @Test
    public void name() {
        //从第0个开始
        System.out.println(MessageFormat.format("测试{0}", "第一个"));
    }
}
