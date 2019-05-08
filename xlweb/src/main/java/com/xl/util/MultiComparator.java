package com.xl.util;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 列表排序组，用于支持多字段排序
 *
 * @author yg.huang
 * @version v1.0
 *          DATE  2015/12/10
 */
public class MultiComparator implements Comparator {
    private Queue<Comparator> comparators = new LinkedList<Comparator>();

    @Override
    public int compare(Object o1, Object o2) {
        Queue<Comparator> comparatorsCopy = new LinkedList<Comparator>();
        comparatorsCopy.addAll(comparators);
        Comparator comparator = comparatorsCopy.poll();
        int compareResult = 0;
        while (comparator != null) {
            if (comparator.compare(o1, o2) > 0) {
                return 1;
            } else if (comparator.compare(o1, o2) < 0) {
                return -1;
            } else {
                compareResult = 0;
                comparator = comparatorsCopy.poll();
            }
        }
        return compareResult;
    }

    public void addComparator(Comparator comparator) {
        this.comparators.add(comparator);
    }

    public Queue<Comparator> getComparators() {
        return comparators;
    }

    public void setComparators(Queue<Comparator> comparators) {
        this.comparators = comparators;
    }
}
