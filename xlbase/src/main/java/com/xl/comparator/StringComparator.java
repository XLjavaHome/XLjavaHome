package com.xl.comparator;

import java.util.Comparator;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @Date: 2019-01-29
 * @Time: 14:29
 * To change this template use File | Settings | File Templates.
 */
public class StringComparator implements Comparator<String> {
    @Override
    public int compare(String a, String b) {
        if (a == null || b == null) {
            return -1;
        }
        return a.compareTo(b);
    }
}
