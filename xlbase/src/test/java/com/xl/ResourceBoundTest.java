package com.xl;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.这个类主要用来解决国际化和本地化问题
 *
 * @author 徐立
 * @date 2019-06-21
 * @time 15:53
 * To change this template use File | Settings | File Templates.
 */
public class ResourceBoundTest {
    /**
     * 其中new Locale(“zh”, “CN”)提供本地化信息，上面这行代码，程序会首先在classpath下寻找my_zh_CN.properties文件，若my_zh_CN.properties文件不存在，则取找my_zh.properties，如还是不存在，继续寻找my.properties,若都找不到就抛出异常。
     */
    @Test
    void name() {
        String baseName = "resources/my";
        //其中new Locale(“zh”, “CN”)提供本地化信息
        Locale locale = new Locale("zh", "CN");
        //找不到资源的话会抛异常
        ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale);
        String cancel = bundle.getString("cancelKey");
        System.out.println(cancel);
        bundle = ResourceBundle.getBundle(baseName, Locale.US);
        cancel = bundle.getString("cancelKey");
        System.out.println(cancel);
        bundle = ResourceBundle.getBundle(baseName, Locale.getDefault());
        cancel = bundle.getString("cancelKey");
        System.out.println(cancel);
        bundle = ResourceBundle.getBundle(baseName, Locale.GERMAN);
        cancel = bundle.getString("cancelKey");
        System.out.println(cancel);
        bundle = ResourceBundle.getBundle(baseName);
        for (String key : bundle.keySet()) {
            System.out.println(bundle.getString(key));
        }
    }
    
    @Test
    void myResourceTest() {
        print(Locale.US);
        print(Locale.getDefault());
        print(Locale.SIMPLIFIED_CHINESE);
    }
    
    private void print(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("com.xl.base.MyResource", locale);
        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            System.out.println(String.format("%1$s : %2$s ", key, bundle.getString(key)));
        }
    }
}
