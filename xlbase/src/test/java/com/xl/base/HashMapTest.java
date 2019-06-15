package com.xl.base;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-02
 * @time 14:11
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class HashMapTest {
    @Test
    public void name() {
        Map map = new HashMap();
        map.put("111", "aaa");
        map.put("222", "bbb");
        map.put("111", "ccc");
        map.containsKey("111");
        log.info(map);
        map.clear();
        log.info(map);
    }
}
