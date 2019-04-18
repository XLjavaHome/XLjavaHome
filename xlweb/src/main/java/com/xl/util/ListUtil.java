package com.xl.util;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import lombok.extern.log4j.Log4j;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * Date: 2017-11-20
 * Time: 14:31
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class ListUtil {
    /**
     * 去重
     *
     * @param list
     * @return
     */
    public static List distinct(List list) {
        //比HashSet效率高
        LinkedHashSet set = new LinkedHashSet(list.size());
        set.addAll(list);
        list.clear();
        list.addAll(set);
        return list;
    }

    public static List getList(int num) {
        List list = new ArrayList(100);
        for (int i = 0; i < num; i++) {
            list.add(i);
        }
        return list;
    }
}
