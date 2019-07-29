package com.xl.translator;

/**
 * 翻译类型
 *
 * @author Created by jz on 2017/10/24 14:47
 */
public enum Language {/**
 * 英语
 */
EN("en"),
    /**
     * 中文
     */
    ZH("zh-CN"),
    /**
     * 俄语
     */
    RU("ru");
    private String english;
    
    Language() {
    }
    
    Language(String english) {
        this.english = english;
    }
    
    public String getEnglish() {
        return english;
    }}

