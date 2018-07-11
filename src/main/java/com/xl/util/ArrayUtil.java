package com.xl.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: 徐立
 * Date: 2017-10-17
 * Time: 17:20
 * To change this template use File | Settings | File Templates.
 */
public class ArrayUtil {
    /**
     * 增加长度
     *
     * @param oldArray
     * @param addLength
     * @return Object
     */
    public static Object arrayAddLength(Object oldArray, int addLength) {
        Class c = oldArray.getClass();
        if (!c.isArray()) {
            return null;
        }
        Class componentType = c.getComponentType();
        int length = Array.getLength(oldArray);
        int newLength = length + addLength;
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(oldArray, 0, newArray, 0, length);
        return newArray;
    }

    /**
     * Description: Array reduce lenght
     *
     * @param oldArray
     * @param reduceLength
     * @return Object
     */
    public static Object arrayReduceLength(Object oldArray, int reduceLength) {
        Class c = oldArray.getClass();
        if (!c.isArray()) {
            return null;
        }
        Class componentType = c.getComponentType();
        int length = Array.getLength(oldArray);
        int newLength = length - reduceLength;
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(oldArray, 0, newArray, 0, newLength);
        return newArray;
    }

    /**
     * 获取int数组中最小的
     *
     * @param arr
     * @return int
     */
    public int getMin(int[] arr) {
        int min = 0;
        for (int x = 1; x < arr.length; x++) {
            if (arr[x] < arr[min]) {
                min = x;
            }
        }
        return arr[min];
    }

    /**
     * 将int数组调换位置
     *
     * @param arr
     * @param a
     * @param b   void
     */
    public void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /**
     * 超过多少就删除
     *
     * @param collection
     * @param max
     */
    public static void delete(Collection collection, int max) {
        if (collection.size() > max) {
            Iterator iterator = collection.iterator();
            int current = 0;
            while (iterator.hasNext()) {
                iterator.next();
                current++;
                if (current >= max) {
                    iterator.remove();
                }
            }
        }
    }
}
