package com.xl.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import lombok.extern.log4j.Log4j;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-03-28
 * @time 22:19
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class ReflectUtil {
    /**
     * 通过反射, 获得Class定义中声明的父类的泛型参数的类型.
     * <p>
     * 注意泛型必须定义在父类处. 这是唯一可以通过反射从泛型获得Class实例的地方.
     * <p>
     * 如无法找到, 返回Object.class.
     * <p>
     * 如public UserDao extends HibernateDao<User,Long>
     *
     * @param clazz clazz The class to introspect
     * @param index the Index of the generic declaration, start from 0.
     * @return the index generic declaration, or Object.class if cannot be determined
     */
    public static Class getClassGenericType(final Class clazz, final int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if ((index >= params.length) || (index < 0)) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }
    
    /**
     * 反射获取所有属性
     *
     * @param obj
     */
    public static void getPropertyGeneric(Object obj) {
        Method[] methods = obj.getClass().getMethods();
        try {
            for (Method method : methods) {
                if (method.getName().startsWith("get") && !StringUtil.equals(method.getName(), "getClass")) {
                    System.out.println(method.getName());
                    Object invoke = method.invoke(obj);
                    log.info(invoke);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
