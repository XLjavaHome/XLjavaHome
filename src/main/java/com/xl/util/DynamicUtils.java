package com.xl.util;

import java.lang.reflect.Method;

public class DynamicUtils {
    /**
     * 动态更新对象（适用于JBoss的对象修改即自动补全对象属性）
     *
     * @param newObj 新对象
     * @param  oriObj 旧对象
     * @return 自动补全后的对象
     */
    static public Object update(Object newObj, Object oriObj) {
        try {
            Method[] method = newObj.getClass().getMethods();
            for (int i = 0; i < method.length; i++) {
                String setMethod = method[i].getName();
                if (setMethod.startsWith("set")) {
                    String fieldName = setMethod.substring(3);
                    Method getMethod = newObj.getClass().getMethod("get" + fieldName);
                    if (getMethod.invoke(newObj) != null && !getMethod.invoke(newObj).equals(getMethod.invoke(oriObj))) {
                        method[i].invoke(oriObj, getMethod.invoke(newObj));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oriObj;
    }
}
