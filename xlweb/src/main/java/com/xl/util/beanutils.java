package com.xl.util;

import com.xl.entity.Person;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 * 使用beanUtils操作bean的属性（第三方jar包）commons-beanutils-1.8.0.jar。还需要支持架 commons-logging.jar 拆分的两个：名字比这个长
 */
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
        BeanUtils.setProperty(p, "name", name);
        BeanUtils.setProperty(p, "password", password);
        BeanUtils.setProperty(p, "age", age);
        BeanUtils.setProperty(p, "birthday", birthday);
        System.out.println(p.getName() + "" + p.getPassword() + "src/src/main" + p.getAge() + ".." + p.getBirthday());
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
                if (!(value instanceof String)) {
                    // throw new CoversionException("只支持String类型转换！！");
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
            BeanUtils.setProperty(p, "name", name);
            BeanUtils.setProperty(p, "password", password);
            BeanUtils.setProperty(p, "age", age);
            BeanUtils.setProperty(p, "birthday", birhday);
            System.out.println(p.getName() + "" + p.getPassword() + "src/src/main" + p.getAge());
            System.out.println(p.getBirthday());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /*
     * 利用Map集合
     */
    @Test
    public void test5() {
        Map<String, String> map = new HashMap<String, String>(0);
        map.put("name", "asda");
        map.put("password", "123");
        map.put("age", "23");
        map.put("birthday", "1980-09-09");
        // 注册转换器
        ConvertUtils.register(new DateLocaleConverter(), Date.class);
        Person bean = new Person();
        try {
            BeanUtils.populate(bean, map);
            System.out.println(bean.getName() + bean.getPassword() + bean.getBirthday());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
