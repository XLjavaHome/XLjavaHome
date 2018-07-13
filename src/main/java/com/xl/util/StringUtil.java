package com.xl.util;

import static com.xl.util.Constant.*;
import java.io.File;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import org.antlr.v4.runtime.misc.Nullable;
import org.apache.commons.lang.ObjectUtils;

/**
 * Title: StringUtil Description: 字符串处理公共组件
 * 直接调用静态方法不会初始化类
 *
 * @author 徐立
 * @version 1.0
 */
public class StringUtil {
    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return boolean
     */
    public static boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return boolean
     */
    public static boolean isEmpty(@Nullable Object str) {
        return null == str || "".equals(str) || "null".equals(str);
    }

    /**
     * 空对象处理
     *
     * @param ob
     * @return
     */
    public static String checkNull(Object ob) {
        if ((null == ob) || ("null".equals(ob)) || ("".equals(ob))) {
            return "";
        } else {
            return ob.toString();
        }
    }

    /**
     * 得到当前工程路径
     *
     * @return
     */
    @Deprecated
    public static String getClassPath() {
        String path = "";
        try {
            path = System.getProperty("user.dir");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return path;
    }

    /**
     * 获取web工程路径：
     *
     * @return
     */
    public static String getWebPath() {
        String s = getWebClassPath();
        String m = rLeft(s, "WEB-INF");
        return m;
    }

    /**
     * 得到ClassPath的绝对路径:web 工程classes路径
     *
     * @return
     */
    public static String getWebClassPath() {
        String result = null;
        try {
            File file = new File(StringUtil.class.getClassLoader().getResource("/").toURI());
            result = file.getAbsolutePath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
     * 从右边向左取分隔符左边的值
     *
     * @param source原始文本 @param sep分隔符 rLeft("1234K5678","K");1234
     */
    public static String rLeft(String source, String sep) {
        source = source.toLowerCase();
        sep = sep.toLowerCase();
        String retstr = "";
        int len = source.length();
        int len1 = sep.length();
        int len2 = len - len1;
        for (int i = len2 - 1; i >= 0; i--) {
            if (source.substring(i, i + len1).equals(sep)) {
                retstr = source.substring(0, i);
                break;
            }
        }
        return retstr;
    }

    /**
     * 中文转换
     *
     * @param strvalue string
     * @return string
     */
    public static String toChinese(String strvalue) {
        try {
            if (null == strvalue) {
                return "";
            } else {
                strvalue = new String(strvalue.getBytes("ISO8859-1"), "gbk");
                return strvalue;
            }
        } catch (Exception e) {
            return "";
        }
    }

    /*
     * 字符串替换方法：区分大小写
     *
     * @param sourceStr String 原始字符串
     * @param oldString String 要被替换的字符串
     * @param newString String 替换的字符串
     * @return String 返回替换后的字符串 replaceStr("1234K5678","K","8888");123488885678
     */
    public static final String replaceStr1(String sourceStr, String oldString, String newString) {
        if (null == sourceStr) {
            return null;
        }
        int i = 0;
        if ((i = sourceStr.indexOf(oldString, i)) >= 0) {
            char[] line2 = sourceStr.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ((i = sourceStr.indexOf(oldString, i)) > 0) {
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        }
        return sourceStr;
    }

    /*
     * 从右边向左取分隔符右边的值
     *
     * @param source原始文本 @param sep分隔符
     *
     * rRight("1234K5678","K");5678
     */
    public static String rRight(String source, String sep) {
        source = source.toLowerCase();
        sep = sep.toLowerCase();
        String retstr = "";
        int len = source.length();
        int len1 = sep.length();
        int len2 = len - len1;
        for (int i = len2 - 1; i >= 0; i--) {
            if (source.substring(i, i + len1).equals(sep)) {
                retstr = source.substring(i + len1, len);
                break;
            }
        }
        return retstr;
    }

    public static String safeToString(Object obj, String s) {
        if (obj == null) {
            return s;
        }
        return obj.toString();
    }

    /**
     * 截取字符串
     *
     * @param str
     * @param start
     * @param end
     * @return
     */
    public static String subString(String str, int start, int end) {
        return subString(str, start, end, "...");
    }

    /**
     * 截取字符串
     *
     * @param str
     * @param start
     * @param end
     * @return
     */
    public static String subString(String str, int start, int end, String patten) {
        if (isEmpty(str)) {
            return "";
        }
        if (str.length() <= end) {
            return str;
        }
        return str.substring(start, end) + patten;
    }

    /**
     * 判断两个字符串是否equals,如果字符串是null,就直接返回false
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, String str2) {
        if (null == str1 || null == str2) {
            return false;
        }
        return str1.equals(str2);
    }

    /**
     * 是否包含另字符串
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean contains(String str1, String str2) {
        if (isEmpty(str1) || isEmpty(str2)) {
            return false;
        }
        return str1.contains(str2);
    }

    public static String join(Collection collection, String separator) {
        return join(collection.iterator(), separator);
    }

    public static String join(Iterator iterator, String separator) {
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return "";
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return ObjectUtils.toString(first);
        }
        // two or more elements
        StringBuffer buf = new StringBuffer(256); // Java default is 16, probably too small
        if (first != null) {
            buf.append(first);
        }
        while (iterator.hasNext()) {
            buf.append(separator);
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }

    /**
     * 添加
     *
     * @param collection
     * @param separator
     * @return
     */
    public static String join(String[] collection, String separator) {
        StringBuffer buf = new StringBuffer(256);
        for (String s : collection) {
            buf.append(s).append(separator);
        }
        buf.deleteCharAt(buf.length() - 1);
        return buf.toString();
    }

    public static String join(Enumeration<String> keys) {
        return join(keys, Constant.COMMA);
    }

    public static String join(Enumeration<String> collection, String separator) {
        StringBuffer buf = new StringBuffer(256);
        if (collection.hasMoreElements()) {
            buf.append(collection.nextElement()).append(separator);
        }
        buf.deleteCharAt(buf.length() - 1);
        return buf.toString();
    }

    /**
     * 是否结束。"."或"。"
     *
     * @param content
     * @return
     */
    public boolean isHasEnding(String content) {
        return content.endsWith(Constant.CHINAFULLPOINT) || content.endsWith(YINGWENJUHAO);
    }
}
