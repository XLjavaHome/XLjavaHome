package com.xl.base.design.template;

/**
 * Created with 徐立.
 *
 * @author: 徐立
 * @Date: 2018-11-22
 * @Time: 22:37
 * To change this template use File | Settings | File Templates.
 */
public abstract class DodishTemplate {
    /**
     * 具体的整个过程
     */
    protected void dodish() {
        this.preparation();
        this.doing();
        this.carriedDishes();
    }
    
    /**
     * 备料
     */
    public abstract void preparation();
    
    /**
     * 做菜
     */
    public abstract void doing();
    
    /**
     * 上菜
     */
    public abstract void carriedDishes();
}
