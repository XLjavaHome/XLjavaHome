package com.xl;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import java.nio.charset.Charset;
import java.util.UUID;
import lombok.extern.log4j.Log4j;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created with 徐立. 需要引入guava
 *
 * @author: 徐立
 * @Date: 2018-12-09
 * @Time: 21:07 To change this template use File | Settings | File Templates.
 */
@Log4j
public class BloomFilterTest {
    private static int size = 1000000;
    private static BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), size);
    
    /**
     * 初始化布隆过滤器~
     */
    @BeforeClass
    public static void before() {
        for (int i = 0; i < size; i++) {
            String object = UUID.randomUUID().toString();
            bloomFilter.put(object);
        }
        bloomFilter.put("这是我的测试");
    }
    
    /**
     *
     */
    @Test
    public void demoTest() {
        long startTime = System.currentTimeMillis(); // 获取开始时间
        //判断这一百万个数中是否包含29999这个数
        containTest("这是我的测试");
        containTest("这是我的测试1");
        long endTime = System.currentTimeMillis();   // 获取结束时间
        log.info("程序运行时间： " + (endTime - startTime) + "毫秒");
    }
    
    private void containTest(String name) {
        if (bloomFilter.mightContain(name)) {
            log.info(name + "命中了");
        } else {
            log.info(name + "没有命中");
        }
    }
}
