package com.xl.util;

/**
 * Created with IntelliJ IDEA.将java转换为html格式
 * User: 徐立
 * Date: 2017-11-06
 * Time: 14:41
 * To change this template use File | Settings | File Templates.
 */
public class HTMLUtil {
    /**
     * 将java转换为html格式
     *
     * @param content
     * @return
     */
    public static String escapeHtml(String content) {
        return content == null ? "" : content.replaceAll(">", "&gt;").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll("\n", "<br/>");
    }
}
