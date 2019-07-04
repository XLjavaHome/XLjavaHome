package com.xl.enumsupport;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-07-04
 * @time 10:38
 * To change this template use File | Settings | File Templates.
 */
public enum FileEnum {fileEnum;
    
    /**
     * 获取换行符
     *
     * @return
     */
    public String getFileSeparator() {
        return System.getProperty("line.separator");
    }}
