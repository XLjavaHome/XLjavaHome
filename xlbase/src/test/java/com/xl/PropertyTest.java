package com.xl;

import com.xl.entity.Person;
import com.xl.entity.PersonUpper;
import com.xl.entity.Teacher;
import com.xl.util.PropertiesUtil;
import java.beans.PropertyDescriptor;
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

/**
 * Created with 徐立. 属性赋值
 *
 * @author 徐立
 * @date 2019-12-12
 * @time 23:11
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class PropertyTest {
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
        Person from = init();
        from.setBirthday(new Date());
        Person to = new Person();
        Person to2 = new Person();
        Map testMap = new HashMap();
        //两种的顺序有区别org.apache.commons.beanutils.BeanUtils.copyProperties第一个参数是目标对象
        //org.springframework.beans.BeanUtils.copyProperties第一个参数是源对象
        org.apache.commons.beanutils.BeanUtils.copyProperties(to, from);
        org.apache.commons.beanutils.BeanUtils.copyProperties(testMap, from);
        log.info(testMap);
        org.springframework.beans.BeanUtils.copyProperties(from, to2);
        testMap.clear();
        org.springframework.beans.BeanUtils.copyProperties(from, testMap);
        log.info(testMap);
        System.out.println(to);
        System.out.println(to2);
    }
    
    private Person init() {
        return new Person("张三", 15);
    }
    
    /*
     * 利用Map集合
     */
    @Test
    public void mapToEntity() throws InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap(10);
        map.put("name", "asda");
        map.put("password", "123");
        map.put("age", "23");
        //这个日期就可以
        String birthday = "birthday";
        //map.put(birthday, new Date());
        //可以需要日期转换器
        //这个不行
        //map.put("birthday", System.currentTimeMillis());
        //map.put(birthday, LocalDate.now());
        dateConverter(map);
        Person bean = new Person();
        BeanUtils.populate(bean, map);
        System.out.println(bean);
    }
    
    private void dateConverter(Map<String, Object> map) {
        map.put("birthday", "1980-09-09");
        // 注册转换器,没有的话无法注册Date对象 会报错的
        DateLocaleConverter converter = new DateLocaleConverter();
        ConvertUtils.register(converter, Date.class);
    }
    
    @Test
    public void property说明Test() {
        PropertyDescriptor[] studentPropertyDescriptors = org.springframework.beans.BeanUtils.getPropertyDescriptors(
                Teacher.class);
        for (PropertyDescriptor studentPropertyDescriptor : studentPropertyDescriptors) {
            System.out.println(studentPropertyDescriptor);
            System.out.println(studentPropertyDescriptor.getReadMethod());
            System.out.println(studentPropertyDescriptor.getWriteMethod());
        }
    }
    
    @Test
    public void 忽略大小的属性复制() throws InvocationTargetException, IllegalAccessException {
        //有属性的实体
        Person person = Person.builder().age(12).name("张三").birthday(new Date()).build();
        //要复制的实体
        PersonUpper personUpper = new PersonUpper();
        //要赋值值的目标对象，
        PropertiesUtil.transferObjectIgnoreCase(person, personUpper);
        System.out.println(personUpper);
        personUpper = new PersonUpper();
        PropertiesUtil.copyProperties(person, personUpper);
        System.out.println(personUpper);
    }
}
