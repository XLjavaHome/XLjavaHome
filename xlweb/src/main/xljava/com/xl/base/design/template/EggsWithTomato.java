package com.xl.base.design.template;
/**
 * Created with 徐立.
 *
 * @author: 徐立
 * @Date: 2018-11-22
 * @Time: 22:39
 * To change this template use File | Settings | File Templates.
 */

/**
 * 西红柿炒蛋
 *
 * @author aries
 */
public class EggsWithTomato extends DodishTemplate {
    @Override
    public void preparation() {
        System.out.println("洗并切西红柿，打鸡蛋。");
    }
    
    @Override
    public void doing() {
        System.out.println("鸡蛋倒入锅里，然后倒入西红柿一起炒。");
    }
    
    @Override
    public void carriedDishes() {
        System.out.println("将炒好的西红寺鸡蛋装入碟子里，端给客人吃。");
    }
}
