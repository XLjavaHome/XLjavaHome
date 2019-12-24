package com.xl.util;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 徐立
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
    public static void copyPropertieswriteProperties(String filePath, String parameterName, String parameterValue,
            String parameterComments) {
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
     * 忽略大小写复制bean的属性
     *
     * @param obj 转换的源对象
     * @param target
     * @return 转换后的对象
     */
    public static <T> void transferObjectIgnoreCase(T obj, T target) {
        if (obj == null || target == null) {
            return;
        }
        try {
            Class<?> clz = target.getClass();
            //获取目标类的属性集合
            Field[] declaredFields = clz.getDeclaredFields();
            Map<String, Field> destPropertyMap = new HashMap<>(declaredFields.length);
            for (Field curField : declaredFields) {
                destPropertyMap.put(curField.getName().toLowerCase(), curField);
            }
            //拷贝属性
            for (Field curField : obj.getClass().getDeclaredFields()) {
                Field targetField = destPropertyMap.get(curField.getName().toLowerCase());
                if (targetField != null) {
                    //反射获取一个方法的访问修饰符
                    if (!Modifier.isPublic(targetField.getModifiers())) {
                        targetField.setAccessible(true);
                    }
                    targetField.set(target, curField.get(obj));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 模仿spring写了一个忽略大小写的属性复制
     *
     * @param source the source bean
     * @param target the target bean
     * @throws BeansException if the copying failed
     */
    public static void copyProperties(Object source, Object target) throws BeansException {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        Class<?> actualEditable = target.getClass();
        //目标属性
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
        Map<String, PropertyDescriptor> sourceMap = Arrays.stream(BeanUtils.getPropertyDescriptors(source.getClass())).collect(
                Collectors.toMap(k -> k.getName().toLowerCase(), v -> v));
        for (PropertyDescriptor targetPd : targetPds) {
            //获取目标的写方法
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null) {
                //根据源目标的属性
                PropertyDescriptor sourcePd = sourceMap.get(targetPd.getName().toLowerCase());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0],
                                                                      readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }
                            Object value = readMethod.invoke(source);
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            writeMethod.invoke(target, value);
                        } catch (Throwable ex) {
                            throw new FatalBeanException(
                                    "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                        }
                    }
                }
            }
        }
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
