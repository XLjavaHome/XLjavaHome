package com.xl.util;

/**
 * File Desc:    日志操作类型
 * Product Name: SIRM
 * Module Name:
 * Author:      王志华
 * History:     11-7-30 created by 王志华
 */
public class LogOperateType {
    /**
     * 插入操作
     */
    public static int INSERT = 1;
    /**
     * 更新操作
     */
    public static int UPDATE = 2;
    /**
     * 删除操作
     */
    public static int DELETE = 3;
    /**
     * 登录操作
     */
    public static int LOGIN = 4;
    /**
     * 注销操作
     */
    public static int LOGOUT = 5;

    private LogOperateType() {
    }
}
