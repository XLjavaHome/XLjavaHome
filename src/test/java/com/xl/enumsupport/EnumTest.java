package com.xl.enumsupport;

import com.xl.util.EnumUtil;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA. 特殊的java类，每一个枚举相当一个枚举实例对象。 构造函数必须私有
 *
 * @author: 徐立
 * @Date: 2018-01-19
 * @Time: 13:17
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class EnumTest {
    @Test
    public void test() {
        // 1. 获取枚举的名称
        log.info(Grade.C.name());
        log.info(Grade.D.getValue());
        log.info(Grade.D.name());
        log.info(Grade.D.localeValue());
        // 2.将字符串改成枚举。valueOf
        String str = "B";
        Grade g = Grade.valueOf(str);
        log.info(g);
        //3.返回所有的枚举值,在文档中没有。
        Grade[] gs = Grade.values();
        for (Grade g1 : gs) {
            log.info("枚举" + g);
        }
    }
    
    @Test
    public void demoTest() {
        System.out.println(EnumUtil.getMap(SIRMPMWhere.class));
        System.out.println(EnumUtil.getMap(Sex.class));
    }
    
    /**
     * 循环枚举
     */
    @Test
    public void forTest() {
        //values获取所有枚举，循环
        SexEnum[] values = SexEnum.values();
        for (SexEnum value : values) {
            System.out.println(value);
        }
    }
}
