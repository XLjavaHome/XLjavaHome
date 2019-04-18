package com.xl.util;

import com.xl.enumsupport.IEnumItem;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.extern.log4j.Log4j;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-05-07
 * @Time: 9:49
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class EnumUtil {
    /**
     * 获取该枚举下的map
     *
     * @param enumClass
     * @return
     */
    public static Map<Integer, String> getMap(Class<? extends IEnumItem> enumClass) {
        Map<Integer, String> resultMap = new LinkedHashMap<>();
        Field[] fields = enumClass.getFields();
        try {
            for (Field field : fields) {
                int fieldModifier = field.getModifiers();
                if (Modifier.isPublic(fieldModifier) && Modifier.isStatic(fieldModifier) && Modifier.isFinal(fieldModifier)) {
                    Class fieldClz = field.getType();
                    // 3 如果该域的类型是IEnumItem，则认定为枚举项
                    if (IEnumItem.class.isAssignableFrom(fieldClz)) {
                        IEnumItem enumItem = (IEnumItem) field.get(null);
                        resultMap.put(enumItem.getEnumItemValue(), enumItem.getEnumItemName());
                    }
                }
            }
        } catch (IllegalAccessException e) {
            log.error(e);
        }
        return resultMap;
    }
}
