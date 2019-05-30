package com.xl.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 徐立
 * Date: 2017-11-20
 * Time: 15:35
 * To change this template use File | Settings | File Templates.
 */
public class RegexUtil {
    /**
     * 匹配IP的正则
     */
    public static final String IP = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
    /**
     * 匹配yyyy-MM-dd
     */
    private static final String DATE_REG = "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";
    /**
     * 匹配yyyy/MM/dd
     */
    private static final String DATE_REG_2 = "^[1-9]\\d{3}/(0[1-9]|1[0-2])/(0[1-9]|[1-2][0-9]|3[0-1])$";
    /**
     * 匹配y/M/d
     */
    private static final String DATE_REG_SIMPLE_2 = "^[1-9]\\d{3}/([1-9]|1[0-2])/([1-9]|[1-2][0-9]|3[0-1])$";
    /**
     * 匹配HH:mm:ss
     */
    private static final String TIME_SEC_REG = "^(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$";
    /**
     * 匹配yyyy-MM-dd HH:mm:ss
     */
    private static final String DATE_TIME_REG =
            "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\\s" + "(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$";
    /**
     * 匹配yyyy-MM-dd HH:mm:ss.SSS
     */
    private static final String DATE_TIME_MSEC_REG =
            "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\\s" + "(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d\\.\\d{3}$";
    /**
     * 匹配yyyy-MM-dd'T'HH:mm:ss.SSS
     */
    private static final String DATE_TIME_MSEC_T_REG =
            "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])T" + "(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d\\.\\d{3}$";
    /**
     * 匹配yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
     */
    private static final String DATE_TIME_MSEC_T_Z_REG =
            "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])T" + "(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d\\.\\d{3}Z$";
    
    /**
     * 正则替换
     *
     * @param charSequence 要替换的字符串
     * @param regex 正则
     * @param afterReplace 替换的字符串
     * @param buf 最后的结果
     */
    public static StringBuffer regularReplacement(CharSequence charSequence, String regex, String afterReplace, StringBuffer buf) {
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(charSequence);
        //必须要加,否则报错
        while (m.find()) {
            //实现非终端添加和替换步骤。
            /* 将当前找到的进行替换并且换好后放到buf中去 */
            m.appendReplacement(buf, afterReplace);
        }
        //实现终端添加和替换步骤。就是最后一段内容追加
        m.appendTail(buf);
        return buf;
    }
}
