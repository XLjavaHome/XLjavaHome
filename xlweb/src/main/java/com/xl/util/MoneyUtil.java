/*
 *货币工具类
 * Amount2RMB.java 2008-6-15
 */
package com.xl.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

public class MoneyUtil {
    private static final Pattern AMOUNT_PATTERN = Pattern.compile("^(0|[1-9]\\d{0,11})\\.(\\d\\d)$"); // 不考虑分隔符的正确性
    private static final char[] RMB_NUMS = "零壹贰叁肆伍陆柒捌玖".toCharArray();
    private static final String[] UNITS = {"元", "角", "分", "整"};
    private static final String[] U1 = {"", "拾", "佰", "仟"};
    private static final String[] U2 = {"", "万", "亿"};
    private static final String[] UNMS = new String[]{"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    private static final String[] DIGITS = new String[]{"", "十", "百", "千"};
    private static final String[] UNITSS = new String[]{"", "万", "亿", "万亿"};
    private static Logger LOGGER = Logger.getLogger(MoneyUtil.class);

    public static String amountToChinese(double amount, boolean withUnit) {
        if (amount == 0) {
            return convert("0.00");
        }
        DecimalFormat format = new DecimalFormat("####0.00");
        String toDo = format.format(amount);
        String result = convert(toDo);
        if (!withUnit) {
            result = result.replaceAll("元", "");
            result = result.replaceAll("角", "");
            result = result.replaceAll("分", "");
            result = result.replaceAll("整", "");
        }
        return result;
    }

    public static String numberToChinese(Integer number) {
        return transfrom(StringUtil.safeToString(number, ""));
    }

    private static String transfrom(String x) {
        if (null == x) {
            return "";
        }
        if (0 == x.length()) {
            return "";
        }
        if (!checkIsNumbers(x)) {
            return "";
        }
        if (x.length() > 16) {
            return "";
        }
        if ("0".equals(x)) {
            return "零";
        }
        //去除字符串首部的0，例如：0010->10
        int fm;
        for (fm = 0; fm < x.length(); fm++) {
            if (x.charAt(fm) != '0') {
                break;
            }
        }
        x = x.substring(fm);//去除完毕
        //把字符串看作一些组，例如：123456789->1,2345,6789
        String result = "";
        int p = 0;
        int m = x.length() % 4;
        int k = (m > 0 ? x.length() / 4 + 1 : x.length() / 4);
        //从最左边的那组开始，向右循环
        for (int i = k; i > 0; i--) {
            int len = 4;
            if (i == k && m != 0)//当i为最左边的那组时，组长度可能有变化
            {
                len = m;
            }
            String s = x.substring(p, p + len);
            int le = s.length();
            for (int j = 0; j < le; j++) {
                int n = Integer.parseInt(s.substring(j, j + 1));
                if (0 == n) {
                    if (j < le - 1 && Integer.parseInt(s.substring(j + 1, j + 2)) > 0 && !result.endsWith(UNMS[0])) {//加零的条件：不为最后一位 && 下一位数字大于0 && 以前没有加过“零”
                        result += UNMS[0];
                    }
                } else {
                    if (!(n == 1 && (result.endsWith(UNMS[0]) || result.length() == 0) && j == le - 2)) {//处理1013一千零"十三"，1113 一千一百"一十三"
                        result += UNMS[n];
                    }
                    result += DIGITS[le - j - 1];
                }
            }
            if (0 != Integer.parseInt(s))//如果这组数字不全是 0 ，则加上单位：万，亿，万亿
            {
                result += UNITSS[i - 1];
            }
            p += len;
        }
        return result;
    }

    //检查字符串s是否全为数字
    private static boolean checkIsNumbers(String x) {
        if (null == x) {
            return false;
        }
        for (Character c : x.toCharArray()) {
            if (c.compareTo('0') < 0 || c.compareTo('9') > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将金额（整数部分等于或少于12位，小数部分2位）转换为中文大写形式.
     *
     * @param amount 金额数字
     * @return 中文大写
     * @throws IllegalArgumentException
     */
    public static String convert(String amount) throws IllegalArgumentException {
        // 去掉分隔符
        amount = amount.replace(",", "");
        // 验证金额正确性
        if ("0.00".equals(amount)) {
            return "零元整";
        }
        Matcher matcher = AMOUNT_PATTERN.matcher(amount);
        if (!matcher.find()) {
            LOGGER.error("输入金额有误，转换失败！[" + amount + "]");
            return "";
            //            throw new IllegalArgumentException("输入金额有误.");
        }
        String integer = matcher.group(1); // 整数部分
        String fraction = matcher.group(2); // 小数部分
        String result = "";
        if (!"0".equals(integer)) {
            result += integer2rmb(integer) + UNITSS[0]; // 整数部分
        }
        if ("00".equals(fraction)) {
            result += UNITSS[3]; // 添加[整]
        } else if (fraction.startsWith("0") && "0".equals(integer)) {
            result += fraction2rmb(fraction).substring(1); // 去掉分前面的[零]
        } else {
            result += fraction2rmb(fraction); // 小数部分
        }
        return result;
    }

    // 将金额小数部分转换为中文大写
    private static String fraction2rmb(String fraction) {
        char jiao = fraction.charAt(0); // 角
        char fen = fraction.charAt(1); // 分
        return (RMB_NUMS[jiao - '0'] + (jiao > '0' ? UNITSS[1] : "")) + (fen > '0' ? RMB_NUMS[fen - '0'] + UNITSS[2] : "");
    }

    // 将金额整数部分转换为中文大写
    private static String integer2rmb(String integer) {
        StringBuilder buffer = new StringBuilder();
        // 从个位数开始转换
        int i, j;
        for (i = integer.length() - 1, j = 0; i >= 0; i--, j++) {
            char n = integer.charAt(i);
            if (n == '0') {
                // 当n是0且n的右边一位不是0时，插入[零]
                if (i < integer.length() - 1 && integer.charAt(i + 1) != '0') {
                    buffer.append(RMB_NUMS[0]);
                }
                // 插入[万]或者[亿]
                if (j % 4 == 0) {
                    if (i > 0 && integer.charAt(i - 1) != '0' || i > 1 && integer.charAt(i - 2) != '0' || i > 2 && integer.charAt(i - 3) != '0') {
                        buffer.append(U2[j / 4]);
                    }
                }
            } else {
                if (j % 4 == 0) {
                    buffer.append(U2[j / 4]);     // 插入[万]或者[亿]
                }
                buffer.append(U1[j % 4]);         // 插入[拾]、[佰]或[仟]
                buffer.append(RMB_NUMS[n - '0']); // 插入数字
            }
        }
        return buffer.reverse().toString();
    }

    public static String formatMoney(Object num) {
        String numStr = "0";
        if (num != null) {
            numStr = num + "";
            String[] numStrs = numStr.split("\\.");
            String left = numStrs[0];
            String right = "0";
            if (numStrs.length == 2) {
                right = numStrs[1];
            }
        }
        return numStr;
    }

    /**
     * 将double类型的数字转换为###,###,###.00的形式
     *
     * @param
     * @return
     */
    public static String formatDoubleToMoney(Object object) {
        String str = "";
        double d = NumberTool.safeToDouble(object, 0.00);
        if (d != 0) {
            NumberFormat numberFormat = DecimalFormat.getCurrencyInstance();
            numberFormat.setRoundingMode(RoundingMode.HALF_UP);
            str = numberFormat.format(d).replace("￥", "");
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println(formatMoney(111111111111111.11111111111111111));
        // Print.print(convert("600000000000"));
        System.out.println(amountToChinese(600000000000.45678d, true));
        System.out.println(convert("600000000000.00"));
        System.out.println("壹万陆仟肆佰零玖元零贰分".equals(convert("16,409.02")));
        System.out.println("壹仟肆佰零玖元伍角".equals(convert("1,409.50")));
        System.out.println("陆仟零柒元壹角肆分".equals(convert("6,007.14")));
        System.out.println("壹仟陆佰捌拾元叁角贰分".equals(convert("1,680.32")));
        System.out.println("叁佰贰拾伍元零肆分".equals(convert("325.04")));
        System.out.println("肆仟叁佰贰拾壹元整".equals(convert("4,321.00")));
        System.out.println("壹分".equals(convert("0.01")));
        System.out.println("壹仟贰佰叁拾肆亿伍仟陆佰柒拾捌万玖仟零壹拾贰元叁角肆分".equals(convert("1234,5678,9012.34")));
        System.out.println("壹仟亿零壹仟万零壹仟元壹角".equals(convert("1000,1000,1000.10")));
        System.out.println("玖仟零玖亿玖仟零玖万玖仟零玖元玖角玖分".equals(convert("9009,9009,9009.99")));
        System.out.println("伍仟肆佰叁拾贰亿零壹万零壹元零壹分".equals(convert("5432,0001,0001.01")));
        System.out.println("壹仟亿零壹仟壹佰壹拾元整".equals(convert("1000,0000,1110.00")));
        System.out.println("壹仟零壹拾亿零壹元壹角壹分".equals(convert("1010,0000,0001.11")));
        System.out.println("壹仟亿元零壹分".equals(convert("1000,0000,0000.01")));
    }
}
