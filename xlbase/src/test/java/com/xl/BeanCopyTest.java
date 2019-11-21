package com.xl;

import com.xl.entity.Person;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立. 复制属性
 *
 * @author 徐立
 * @date 2019-11-08
 * @time 15:23
 * To change this template use File | Settings | File Templates.
 */
public class BeanCopyTest {
    private Map map;
    private Person p;
    
    @BeforeEach
    void setUp() {
        map = new HashMap();
        map.put("name", "张三");
        p = new Person();
    }
    
    @Test
    void apache() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        PropertyUtils.copyProperties(p, map);
        System.out.println(p);
    }
    
    /**
     * spring的效率会高
     */
    @Test
    void spring() {
        org.springframework.beans.BeanUtils.copyProperties(map, p);
        System.out.println(p);
    }
}
