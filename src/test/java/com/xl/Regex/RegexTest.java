package com.xl.Regex;

import com.xl.util.FileUtil;
import com.xl.util.RegexUtil;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

@Log4j
public class RegexTest {
    /**
     * \b单词的边界
     * Pattern.compile("\\B").split(str) 正则分割
     */
    @Test
    public void 正则中b测试() {
        String str = "(中文问号？123???英文)问号?我是华丽[的制表符\t]我是华丽{的空格符 我是华丽}的换行符\n";
        //单词的边界
        String[] result = Pattern.compile("\\b").split(str);
        for (String string : result) {
            log.info("分割的字符串:" + "[" + string + "]");
        }
        String[] result2 = Pattern.compile("\\B").split(str);
        for (String string : result2) {
            log.info("分割的字符串:" + "[" + string + "]");
        }
    }
    
    @Test
    public void regexTest() {
        String mystr = "   11?s   342  ";
        Matcher m = Pattern.compile("^[\\u3000\\s\\t]*(.*?)[\\u3000\\s\\t]*$").matcher(mystr);
        if (m.find()) {
            System.out.println(m.group(1));
        }
    }
    
    @Test
    public void search1() {
        Pattern p = Pattern.compile("java");
        Matcher m = p.matcher("java Java JAVa qjaval JaVa IloveJAVA you hateJava afasdfasdf");
        while (m.find()) {
            System.out.println(m.group());
        }
    }
    
    /*
     * Pattern.
     * CASE_INSENSITIVE
     * :
     * 常量规则：
     * 忽大小写
     */
    @Test
    public void search2() {
        Pattern p = Pattern.compile("java", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher("java Java JAVa JaVa IloveJAVA you hateJava afasdfasdf");
        //        System.out.println(p.matcher("jaVa").matches());
        while (m.find()) {
            System.out.println(m.group());
        }
    }
    
    @Test
    public void search3() {
        String regex = "java";
        String string = "java Java JAVa JaVa IloveJAVA you hateJava afasdfasdf";
        String afterReplace = "888";
        StringBuffer buf = new StringBuffer();
        RegexUtil.regularReplacement(string, regex, afterReplace, buf);
        System.out.println(buf);
    }
    
    /**
     * 文本内容，正则匹配，文件生成
     * <p>
     * 正则替换
     *
     * @throws IOException
     */
    @Test
    public void regularReplacement() throws IOException {
        StringBuilder textContent = FileUtil.getContent("D:\\code\\XLjavaHome\\src\\main\\resources\\doc\\xl.txt");
        //懒加载要?匹配
        String regex = "create table.*?;";
        StringBuffer result = new StringBuffer();
        RegexUtil.regularReplacement(textContent, regex, "", result);
        System.out.println(result);
        FileUtil.write(FileUtil.createTempFile("1.txt"), result.toString());
    }
    
    /**
     * 分组测试
     */
    @Test
    public void group1() {
        Pattern p = Pattern.compile("\\d{3,5}[a-z]{2}");
        p = Pattern.compile("(\\d{1})([a-z])");
        String s = "123aa-34345bb-234cc-00";
        Matcher m = p.matcher(s);
        while (m.find()) {
            System.out.println("第0组即是所匹配的字符串:" + m.group(0));
            System.out.println("第1组即是第1个()内字符串:" + m.group(1));
            System.out.println("第2组即是第2个()内字符串:" + m.group(2));
        }
    }
    
    @Test
    public void 常用() {
        // 3到10个字符后面跟着一位数字:没有问号：一次吃10个字符发现不匹配于是吐出一个字符再去匹配
        Pattern p = Pattern.compile("[a-z]{3,10}[0-9]");
        String s = "aaaa5bbbb6";
        Matcher m = p.matcher(s);
        if (m.find()) {
            System.out.println(m.start() + "-" + m.end());
        } else {
            System.out.println("not match!");
        }
    }
    
    /* 勉强模式 */
    @Test
    public void fun2() {
        Pattern p = Pattern.compile(".{3,10}?[0-9]");
        /* 第一次吃3个字符看符合不？此时第四个表示数子，因此不符合 ，再吃进一位：此时符合 */
        String s = "aaaa5bbbb68";
        Matcher m = p.matcher(s);
        if (m.find()) {
            System.out.println(m.start() + "-" + m.end());
        } else {
            System.out.println("not match!");
        }
    }
    
    /* 一般不用:占有模式 */
    @Test
    public void fun3() {
        Pattern p = Pattern.compile(".{3,10}+[0-9]");/* 一次吃10个字符并且不往外吐 */
        String s = "aaaa5bbbb6";
        Matcher m = p.matcher(s);
        if (m.find()) {
            System.out.println(m.start() + "-" + m.end());
        } else {
            System.out.println("not match!");
        }
    }
    
    @Test
    public void fun4() {
        Pattern p = Pattern.compile(".{3}"); // 匹配3个字符串
        String s = "444a66b";
        Matcher m = p.matcher(s);
        while (m.find()) {
            System.out.println(m.group());
        }
    }
    
    @Test
    public void fun5() {
        Pattern p = Pattern.compile(".{2,8}(?=a)"); // (?=a)：表示非捕获组(不捕获a)：表示结束的字符是a
        String s = "444a66b";
        Matcher m = p.matcher(s);
        while (m.find()) {
            System.out.println(m.group());
        }
    }
    
    @Test
    public void fun6() {
        Pattern p = Pattern.compile("(?=a).{3}");
        // (?=a)：表示非捕获组(不捕获a)：表示以a打头后面三个字符 ,包括a
        String s = "444a66b";
        Matcher m = p.matcher(s);
        while (m.find()) {
            System.out.println(m.group());
        }
    }
    
    @Test
    public void fun7() {
        Pattern p = Pattern.compile(".{3}(?=!a)");
        String s = "444a66b";
        Matcher m = p.matcher(s);
        while (m.find()) {
            System.out.println(m.group());
        }
    }
    
    @Test
    public void fun8() {
        Pattern p = Pattern.compile(".{3}(?<=a)"); /* 从后往前数还包含a的 */
        String s = "444a66b";
        Matcher m = p.matcher(s);
        while (m.find()) {
            System.out.println(m.group());
        }
    }
    
    /**
     * 向前引用
     */
    @Test
    public void fun9() {
        Pattern p = Pattern.compile("(\\d\\d)\\1");/* 1:表示后面找到的结果必须和前面找到的一模一样 */
        String s = "232312";
        Matcher m = p.matcher(s);
        System.out.println(m.matches());
    }
    
    /**
     * 向前引用
     */
    @Test
    public void fun10() {
        Pattern p = Pattern.compile("(\\d(\\d))\\2");
        // (\\d(\\d)):这里是两个组：第二个组抓到的是2 后面的\\2:表示后面的要和第二个抓到数字一样
        String s = "122";
        Matcher m = p.matcher(s);
        System.out.println(m.matches() + ":" + m.group(2));
    }
    
    @Test
    public void fun11() {
        Pattern p = Pattern.compile("java", Pattern.CASE_INSENSITIVE);
        System.out.println("Java".matches("(?i)(java)")); /* (?i)非捕获组:是上面的简写 */
    }
    
    /**
     * 非贪婪模式
     */
    @Test
    public void greedyTest() {
        String reg = "\\$\\{.*?}";
        String str = "${测试232}23r  ${wqq}";
        Pattern compile = Pattern.compile(reg);
        Matcher matcher = compile.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
