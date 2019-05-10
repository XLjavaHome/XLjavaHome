package com.xl.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;

/**
 * @author 徐立
 * @Decription 正则工具类
 * @date 2013-12-21
 */
public class RegTool {
    public static boolean checkMail(String mail) {
        // 相对精确的匹配
        String reg = "[a-zA-Z0-9_]+@[a-zA-Z0-9]+(\\.[a-zA-Z]+){1,3}"; // 6到12位的用户名
        // 相对笼统的匹配
        reg = "\\w+@\\w+(\\.\\w+)+";
        return mail.matches(reg);
        // 比较叼的匹配
        //		 while(mail.indexOf("@")!=-1&&mail.indexOf("."))
    }

    /**
     * 得到所有的中文
     *
     * @param content
     * @return
     */
    public static String[] getChinese(String content) {
        return getContent(content, "[\\u4E00-\\u9FFF]+");
    }

    /**
     * 返回所有符合条件的
     *
     * @param content
     * @param regex
     * @return
     */
    public static String[] getContent(String content, String regex) {
        return getContent(content, regex, 0);
    }

    /**
     * 返回符合条件的集合
     *
     * @param content 内容
     * @param regex   正则
     * @param params  组别
     * @return List<String>
     */
    public static String[] getContent(String content, String regex, int... params) {
        List<String> list = new ArrayList<String>();
        Pattern p = Pattern.compile(regex,// Pattern.MULTILINE | //多行模式
                // Pattern.DOTALL | //匹配任何字符，包括换行符。
                Pattern.UNICODE_CASE);
        Matcher m = p.matcher(content);
        while (m.find()) {
            for (int i = 0; i < params.length; i++) {
                list.add(m.group(params[i]));
            }
        }
        String[] strs = new String[list.size()];
        return list.toArray(strs);
    }

    /**
     * 返回http
     *
     * @param content
     * @return List<String>
     */
    public static String[] getHttpUrl(String content) {
        return getContent(content, "(http://.+)[\"|']", 1);
    }

    public static boolean isHttpUrl(String content) {
        return isMatch(content, "http://\\w+");
    }

    /**
     * 是否匹配指定规则
     *
     * @param s
     * @param reg
     * @return
     */
    public static boolean isMatch(String s, String reg) {
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(s);
        return m.find();
    }

    /**
     * 匹配所有的图片格式
     *
     * @param content
     * @return
     */
    public static String[] getImageURL(String content) {
        return getContent(content, "(http://.+?[jpg][pni][gf])");
    }

    /**
     * 判断是否中文
     *
     * @param s
     * @return
     */
    public static boolean isChinese(String s) {
        return Pattern.matches("[\u4E00-\u9FFF]+", s);
    }

    /**
     * 去两个以上的空格
     *
     * @param content
     * @return String
     */
    public static String trimTwo(String content) {
        return content.replaceAll("\\s{2,}", "");
    }

    /**
     * 去掉空格
     *
     * @param content
     * @return
     */
    public static String trim(String content) {
        return content.replaceAll("\\s", "");
    }

    // -----------------------------------这是测试------------------------------------------------
    @Test
    public void testGetEmail() {
        String s = "潍坊1239s我仿佛dfs0812093@qq.com";
        String[] strs = getEmail(s);
        System.out.println(Arrays.toString(strs));
    }

    /**
     * 匹配所有的Email
     *
     * @param content
     * @return
     */
    public static String[] getEmail(String content) {
        return getContent(content, "[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+");
    }
}
