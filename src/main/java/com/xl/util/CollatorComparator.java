package com.xl.util;
/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-10-29
 * Time: 下午7:54
 * To change this template use File | Settings | File Templates.
 */

import java.text.CollationKey;
import java.text.Collator;
import java.util.Comparator;

public class CollatorComparator implements Comparator {
    Collator collator = Collator.getInstance();

    @Override
    public int compare(Object element1, Object element2) {
        CollationKey key1 = collator.getCollationKey(element1.toString());
        CollationKey key2 = collator.getCollationKey(element2.toString());
        return key1.compareTo(key2);
    }
}
