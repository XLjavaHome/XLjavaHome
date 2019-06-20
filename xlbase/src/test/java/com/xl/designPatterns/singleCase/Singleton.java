package com.xl.designPatterns.singleCase;

/**
 * Created with 徐立.用枚举创建单例
 *
 * @author 徐立
 * @date 2019-06-20
 * @time 9:19
 * To change this template use File | Settings | File Templates.
 */
public enum Singleton {
    instance;
    
    public void sout() {
        System.out.println("世界，你好");
    }
}
