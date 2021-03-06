package com.xl.enumsupport;

import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA.字符集枚举
 *
 * @author 徐立
 * @Date: 2018-06-20
 * @Time: 10:39
 * To change this template use File | Settings | File Templates.
 */
public enum CharacterEnum {
    UTF8("UTF-8") {
    }, GBK("GBK") {
    };
    private String value;
    
    public Charset getCharset() {
        return Charset.forName(this.getValue());
    }
    CharacterEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
