package com.xl.reflect;

import org.junit.Test;

import java.lang.reflect.Method;

public class InvokeSetAndGet {
    @Test
    public void main() {
        Class<?> demo = null;
        Object obj = null;
        try {
            demo = Class.forName("reflect.Person");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            obj = demo.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setter(obj, "name", "男", String.class);
        getter(obj, "name");
    }

    /**
     * @param obj   操作的对象
     * @param att   操作的属性
     * @param value 设置的值
     * @param type  参数的属性
     */
    public void setter(Object obj, String att, Object value, Class<?> type) {
        try {
            att = toUp(att);
            Method method = obj.getClass().getMethod("set" + att, type);
            method.invoke(obj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param obj 操作的对象
     * @param att 操作的属性
     */
    public void getter(Object obj, String att) {
        try {
            att = toUp(att);
            Method method = obj.getClass().getMethod("get" + att);
            System.out.println(method.invoke(obj));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将第一个字母改成大写
     *
     * @param content
     */
    private String toUp(String content) {
        StringBuffer sb = new StringBuffer(content);
        String s = sb.substring(0, 1);
        s = s.toUpperCase();
        sb.replace(0, 1, s);
        return sb.toString();
    }
}
