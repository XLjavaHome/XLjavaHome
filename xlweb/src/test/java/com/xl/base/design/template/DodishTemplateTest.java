package com.xl.base.design.template;

import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author: 徐立
 * @Date: 2018-11-22
 * @Time: 22:37
 * To change this template use File | Settings | File Templates.
 */
public class DodishTemplateTest {
    /**
     * 模板设计模式,调用抽象类方法       <p/>
     * 定义一个操作中算法的骨架，而将一些步骤延迟到子类中，模板方法使得子类可以不改变算法的结构即可重定义该算法的某些特定步骤。
     */
    @Test
    public void demoTest() {
        DodishTemplate eggsWithTomato = new EggsWithTomato();
        eggsWithTomato.dodish();
        System.out.println("-----------------------------");
        DodishTemplate bouilli = new Bouilli();
        bouilli.dodish();
    }
}