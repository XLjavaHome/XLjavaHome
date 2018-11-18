package com.xl.service.impl;

import com.xl.service.Subject;
import lombok.extern.log4j.Log4j;

/**
 * Created with 徐立.
 *
 * @author: 徐立
 * @Date: 2018-11-17
 * @Time: 10:43
 * To change this template use File | Settings | File Templates.
 */ // 定义真实项目
@Log4j
public class RealSubject implements Subject {
    @Override
    public String say(String name, int age) {
        log.info(name + "  " + age);
        return name + "  " + age;
    }
}
