package com.xl.util;

import com.xl.entity.Person;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.log4j.Log4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.junit.Test;

/*
 * 使用beanUtils操作bean的属性（第三方jar包）commons-beanutils-1.8.0.jar。还需要支持架 commons-logging.jar 拆分的两个：名字比这个长
 */
@Log4j
public class beanutils {
    @Test
    public void setProperty() throws IllegalAccessException, InvocationTargetException {
        Person p = new Person();
        // 设置属性
        BeanUtils.setProperty(p, "name", "赋值的姓名1");
        System.out.println(p.getName());
    }

    /*
     * 好处：直接将整型123，赋值给了int age，支持8种基本数据类型。不支持日期
     */
    @Test
    public void Test2() throws IllegalAccessException, InvocationTargetException {
        String name = "aaaa";
        String password = "123";
        String age = "34";
        String birthday = "1990-02-28";
        Person p = new Person();
        setPropty(name, password, age, birthday, p);
        System.out.println(p.getName() + "" + p.getPassword() + "src/src/main" + p.getAge() + ".." + p.getBirthday());
    }
    
    private void setPropty(String name, String password, String age, String birhday,
            Person p) throws IllegalAccessException, InvocationTargetException {
        BeanUtils.setProperty(p, "name", name);
        BeanUtils.setProperty(p, "password", password);
        BeanUtils.setProperty(p, "age", age);
        BeanUtils.setProperty(p, "birthday", birhday);
    }
    
    /*
     *
     */
    @Test
    public void test3() {
        String name = "aaaa";
        String password = "123";
        String age = "34";
        String birhday = "1980-09-09";
        Person p = new Person();
        // 为了让日期到bean的birhday属性上，我们给beanUtils注册一个日期转换器
        // 接口不能new的原因是里面有没有实现的方法，只要把方法实现了就能new了
        ConvertUtils.register(new Converter() {
            @Override
            public Object convert(Class type, Object value) {
                if (value == null) {
                    return null;
                }
                String str = (String) value;
                if ("".equals(str.trim())) {
                    return null;
                }
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    return df.parseObject(str);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }, Date.class);
        try {
            setPropty(name, password, age, birhday, p);
            System.out.println(p);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 复制
     */
    @Test
    public void copyTest() throws InvocationTargetException, IllegalAccessException {
        Person from = new Person(15, "张三");
        from.setBirthday(new Date());
        Person to = new Person();
        Person to2 = new Person();
        Map testMap = new HashMap();
        //两种的顺序有区别org.apache.commons.beanutils.BeanUtils.copyProperties第一个参数是目标对象
        //org.springframework.beans.BeanUtils.copyProperties第一个参数是源对象
        BeanUtils.copyProperties(to, from);
        BeanUtils.copyProperties(testMap, from);
        log.info(testMap);
        org.springframework.beans.BeanUtils.copyProperties(from, to2);
        testMap.clear();
        org.springframework.beans.BeanUtils.copyProperties(from, testMap);
        log.info(testMap);
        System.out.println(to);
        System.out.println(to2);
    }
    
    /*
     * 利用Map集合
     */
    @Test
    public void mapToEntity() throws InvocationTargetException, IllegalAccessException {
        Map<String, String> map = new HashMap<String, String>(10);
        map.put("name", "asda");
        map.put("password", "123");
        map.put("age", "23");
        map.put("birthday", "1980-09-09");
        // 注册转换器,没有的话无法注册Date对象 会报错的
        DateLocaleConverter converter = new DateLocaleConverter();
        ConvertUtils.register(converter, Date.class);
        Person bean = new Person();
        BeanUtils.populate(bean, map);
        System.out.println(bean);
    }
}
