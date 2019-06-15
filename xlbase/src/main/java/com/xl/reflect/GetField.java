package com.xl.reflect;

import com.xl.entity.Person;
import java.lang.reflect.Field;
import org.junit.Test;

public class GetField {
    private String className = "reflect.Person";

    /*
     * 获取公有字段
     */
    @Test
    public void test1() throws ClassNotFoundException, NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException {
        Person p = new Person();
        Class clazz = Class.forName(className);
        Field field = clazz.getField("name"); // 传属性名
        String name = (String) field.get(p);
        System.out.println(name);
        //得到属性的值
        //Print.print(f.get(f));
        /*
         * 如果不知道字段类型
		 */
        Class type = field.getType();
        System.out.println(type);
        Object value = field.get(p);
        Object obj = field.get(p);
        if (type.equals(String.class)) {
            String svalue = (String) value;
            System.out.println(svalue);
        }

		/*
         * 设置字段的值
		 */
        field.set(p, "xxx");
        System.out.println(p.name);
    }

    /*
     * 获取私有的字段
     */
    @Test
    public void test2() throws Exception {
        Person p = new Person();
        Class clazz = Class.forName(className);
        Field f = clazz.getDeclaredField("password");
        f.setAccessible(true);
        System.out.println(f.get(p));
    }
    /*
     * 获取静态的字段:和上面一样，不能省略对象。
	 */
}
