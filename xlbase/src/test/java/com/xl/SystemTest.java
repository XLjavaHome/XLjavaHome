package com.xl;

import java.util.Map;
import java.util.Properties;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-30
 * @time 14:04
 * To change this template use File | Settings | File Templates.
 */
public class SystemTest {
    /**
     * 获取系统的环境变量
     */
    @Test
    void getEnvironmentPath() {
        Map<String, String> envmap = System.getenv();
        envmap.entrySet().stream().map(entry -> entry.getKey() + "--->" + entry.getValue()).forEach(System.out::println);
    }
    
    @Test
    void getPath2() {
        Properties properties = System.getProperties();
        System.out.println(properties);
    }
    
    @Test
    void name() {
        //系统计时器的当前值，以毫微秒为单位。
        System.out.println(System.nanoTime());
        //返回以毫秒为单位的当前时间。
        System.out.println(System.currentTimeMillis());
    }
}
