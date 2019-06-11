package com.xl.service.impl;

import com.xl.service.Subject;
import lombok.extern.log4j.Log4j;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @Date: 2018-11-17
 * @Time: 10:43
 * To change this template use File | Settings | File Templates.
 */ // 定义真实项目
@Log4j
public class RealSubject implements Subject {
    @Override
    public String say(String name, int age) {
        log.info(name + "  " + age);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return name + "  " + age;
    }
    
    @Override
    public void eat() {
        log.info("测试eat方法是否执行");
    }
    
    @Override
    public void exception() throws Exception {
        try {
            System.out.println(1 / 0);
        } catch (Exception e) {
            log.info("除以0", e);
        }
    }
}
