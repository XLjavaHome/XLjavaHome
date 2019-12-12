package com.xl.base;

import lombok.extern.log4j.Log4j;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-02
 * @time 22:30
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class SwichTest {
    @Test
    public void demo1() {
        //根据hashCode来实现的
        switch ("b") {
            case "a":
                String 成功了 = "成功了";
                log.info(成功了);
                break;
            case "b":
                log.info("这是b");
                break;
            default:
                log.info("defult");
        }
    }
    
    /**
     * swich空的话会报错
     */
    @Test
    public void nullTest() {
        String b = null;
        switch (b) {
            case "a":
                String 成功了 = "成功了";
                log.info(成功了);
                break;
            case "b":
                log.info("这是b");
                break;
            default:
                log.info("defult");
        }
    }
}
