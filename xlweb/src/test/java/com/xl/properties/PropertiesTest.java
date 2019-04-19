package com.xl.properties;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-02-08
 * @Time: 16:55
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class PropertiesTest {
    /**
     * 用属性的store方法生成
     *
     * @throws IOException
     */
    @Test
    public void name() throws IOException {
        Properties prop = new Properties();
        ///保存属性到b.properties文件
        String name = "b.properties";
        File file = new File(name);
        FileOutputStream fileOutputStream = new FileOutputStream(file, false);//true表示追加打开
        prop.setProperty("phone", "10086");
        String comments = "这是注释";
        comments = null;
        prop.store(fileOutputStream, comments);
        System.out.println(file.getAbsolutePath());
    }
}
