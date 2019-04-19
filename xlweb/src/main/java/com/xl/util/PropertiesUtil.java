package com.xl.util;

import java.io.*;
import java.util.*;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang.StringUtils;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-02-08
 * @Time: 16:39
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class PropertiesUtil {
    /**
     * 写入属性值
     * <p>
     * 方法说明:
     * </p>
     * <li></li>
     *
     * @param filePath
     * @param parameterName
     * @param parameterValue
     * @param parameterComments
     * @return void
     * @auther DuanYong
     * @since 2013-3-11 下午2:41:59
     */
    public static void writeProperties(String filePath, String parameterName, String parameterValue, String parameterComments) {
        Properties prop = new Properties();
        try {
            InputStream fis = new FileInputStream(filePath);
            // 从输入流中读取属性列表（键和元素对）
            prop.load(fis);
            // 调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
            // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
            OutputStream fos = new FileOutputStream(filePath);
            prop.setProperty(parameterName, parameterValue);
            // 以适合使用 load 方法加载到 Properties 表中的格式，
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
            prop.store(fos, parameterComments);
            log.info("写入属性配置文件:" + filePath + "成功,属性名称：" + parameterName + ",属性值：" + parameterValue + ",属性注释："
                     + parameterComments);
        } catch (IOException e) {
            log.error("写入属性配置文件:" + filePath + "失败,属性名称：" + parameterName + ",属性值：" + parameterValue + ",属性注释："
                      + parameterComments);
            e.printStackTrace();
        }
    }
    
    /**
     * 写入属性
     * <p>方法说明:</p>
     * <li></li>
     *
     * @param filePath
     * @param propertiesMap
     * @param parameterComments
     * @return void
     * @auther DuanYong
     * @since 2013-3-11 下午8:22:20
     */
    public static void writeMapProperties(String filePath, Map<String, String> propertiesMap, String parameterComments) {
        Properties prop = new Properties();
        try {
            InputStream fis = new FileInputStream(filePath);
            // 从输入流中读取属性列表（键和元素对）
            prop.load(fis);
            // 调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
            // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
            OutputStream fos = new FileOutputStream(filePath);
            String key = "";
            for (Iterator<String> it = propertiesMap.keySet().iterator(); it.hasNext(); ) {
                key = it.next();
                prop.setProperty(key, propertiesMap.get(key));
                log.info("写入属性配置文件:" + filePath + "成功,属性名称：" + key + ",属性值：" + propertiesMap.get(key));
            }
            prop.store(fos, parameterComments);
        } catch (IOException e) {
            log.error("写入属性配置文件:" + filePath + "失败");
            e.printStackTrace();
        }
    }
    
    public static Map<String, String> readProperties(File resourceFile) {
        return readProperties(resourceFile.getPath());
    }
    
    public static Map<String, String> readProperties(String filePath) {
        Map<String, String> propertiesMap = new HashMap<String, String>();
        Properties props = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            props.load(in);
            Enumeration<?> en = props.propertyNames();
            String key = null;
            String value = null;
            while (en.hasMoreElements()) {
                key = (String) en.nextElement();
                value = props.getProperty(key);
                if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
                    propertiesMap.put(key, value);
                }
            }
        } catch (Exception e) {
            log.info("读取属性配置文件:" + filePath + "失败");
        }
        return propertiesMap;
    }
    
    /**
     * 加载属性配置文件
     * <p>方法说明:</>
     * <li></li>
     *
     * @param is
     * @return
     */
    public static Properties loadProperties(InputStream is) {
        Properties properties = new Properties();
        try {
            properties.load(is);
            return properties;
        } catch (IOException e) {
            log.error("加载配置文件失败", e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception ex) {
                log.error(ex);
            }
        }
        return null;
    }
    
    /**
     * 将属性配置转换为map
     *
     * @return map
     */
    public Map<String, String> propertiesToMap(String path, Properties properties) {
        Map<String, String> propertiesMap = new HashMap<String, String>();
        Enumeration<?> propNames = properties.propertyNames();
        String propKey = null;
        String propValue = null;
        while (propNames.hasMoreElements()) {
            propKey = (String) propNames.nextElement();
            propValue = properties.getProperty(propKey);
            if (StringUtil.isNotEmpty(propKey)) {
                propertiesMap.put(propKey, properties.getProperty(propKey));
            }
        }
        return propertiesMap;
    }
}
