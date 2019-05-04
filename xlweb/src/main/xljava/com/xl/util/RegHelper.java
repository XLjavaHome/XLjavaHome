package com.xl.util;

/**
 * @author 徐立
 * @Decription 匹配复杂的正则
 * @date 2014-1-25
 */
public class RegHelper {
    private String reg;

    public RegHelper() {
    }

    public RegHelper(String reg) {
        this.reg = reg;
    }

    public RegHelper append(String reg) {
        this.reg = this.reg + ".+?" + reg;
        return this;
    }

    @Override
    public String toString() {
        return reg;
    }
}
