package com.xl.base;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-08-22
 * @time 10:07
 * To change this template use File | Settings | File Templates.
 */
public class MapTest {
    @Test
    void caseInsensitiveMap() {
        CaseInsensitiveMap map = new CaseInsensitiveMap();
        map.put("hello", 1);
        map.put("Hello", 2);
        System.out.println(map);
    }
}
