package com.xl.enumsupport;

/**
 * Created with IntelliJ IDEA.字符集枚举
 *
 * @author 徐立
 * @Date: 2018-06-20
 * @Time: 10:39
 * To change this template use File | Settings | File Templates.
 */
public enum CharsetEnum {
    UTF8("UTF-8") {
    }, GBK("GBK") {
    };
    private String value;

    CharsetEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
