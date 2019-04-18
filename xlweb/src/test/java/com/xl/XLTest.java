package com.xl;

import com.google.common.annotations.VisibleForTesting;
import com.xl.collections.CaseInsensitiveMap;
import com.xl.util.StringUtil;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import sun.text.resources.cldr.aa.FormatData_aa;

/**
 * Created with IntelliJ IDEA.
 * User: 徐立
 * Date: 2017/9/28
 * Time: 17:53
 * To change this template use File | Settings | File Templates.
 */
@Log4j
//可见测试
@VisibleForTesting
public class XLTest {
    private String myMap;
    
    /**
     * 不同的值能不能内联？
     * 可以
     */
    @Test
    public void inLineTest() {
        boolean x = true;
        System.out.println(x);
        System.out.println(false);
    }
    
    @Test
    public void FormatData_aaTest() {
        FormatData_aa aa = new FormatData_aa();
        System.out.println(aa.getLocale());
        System.out.println(StringUtil.join(aa.getKeys()));
    }
    
    @Test
    public void mapTest() {
        Map map = new CaseInsensitiveMap();
        map.put("aaa", 11);
        map.put("aAA", 22);
        map.put("aAA", "测试");
        map.put("aA1A", "测试");
        map.put("aA1A", "测试2");
        log.info(map);
        log.info(map);
        System.out.println(map);
        System.out.println("这是输出");
    }
    
    @Test
    public void desktopTest() throws IOException {
        Desktop.getDesktop().browse(URI.create("http://127.0.0.1:8080"));
    }
    
    public static void testBrowser(WebDriver driver) throws Exception
    {
        driver.get("http://www.cnblogs.com/tankxiao");
        Thread.sleep(5000);
        // 浏览器最大化
        driver.manage().window().maximize();
        
        driver.navigate().to("http://www.baidu.com");
        // 刷新浏览器
        driver.navigate().refresh();
        // 浏览器后退
        driver.navigate().back();
        // 浏览器前进
        driver.navigate().forward();
        // 浏览器退出
        //driver.quit();
    }
    @Test
    public void 测试() throws Exception {
        testBrowser(new ChromeDriver());
        
    }
    
    @Test
    public void convertTest() {
        List<String> collected = new ArrayList<>();
        collected.add("alpha");
        collected.add("beta");
        collected = collected.stream().map(string -> string.toUpperCase()).collect(Collectors.toList());
        System.out.println(collected);
    }
    
    @Test
    public void name() {
        System.out.println("11.asdb".matches("\\d+\\..*"));
    }
    
    /**
     * 改成冒号了
     */
    @Test
    public void convertTest2() {
        List<String> collected = new ArrayList<>();
        collected.add("alpha");
        collected.add("beta");
        for (int i = 0; i < 10000; i++) {
            collected.add(i + "");
        }
        collected = collected.stream().map(String::toUpperCase).collect(Collectors.toCollection(ArrayList::new));//注意发生的变化
        System.out.println(collected);
    }
    
    @Test
    public void convertTest3() {
        List<String> collected = new ArrayList<>();
        collected.add("alpha");
        collected.add("beta");
        for (int i = 0; i < 10000; i++) {
            collected.add(i + "");
        }
        for (String s : collected) {
            s = s.toUpperCase();
        }
        System.out.println(collected);
    }
}

