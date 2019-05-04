package com.xl.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import lombok.extern.log4j.Log4j;

/**
 * Created with IntelliJ IDEA.
 * User: 徐立
 * Date: 2017-10-17
 * Time: 17:20
 * To change this template use File | Settings | File Templates.
 */
@Log4j
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
     * @param b void
     */
    public static void swap(int[] arr, int a, int b) {
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
    
    /**
     * 快速排序 :设要排序的数组是A[0]……A[N-1]，首先任意选取一个数据（通常选用数组的第一个数）作为关键数据，然后将所有比它小的数都放到它前面，所有比它大的数都放到它后面，这个过程称为一趟快速排序。值得注意的是，快速排序不是一种稳定的排序算法，也就是说，多个相同的值的相对位置也许会在算法结束时产生变动
     *
     * @param data
     * @param left
     * @param right
     */
    public static void fastSort(int[] data, int left, int right) {
        //基准数
        int base = data[left];
        int ll = left;
        int rr = right;
        while (ll < rr) {
            //如果右边的数比基准数大就换位
            while (ll < rr && data[rr] >= base) {
                rr--;
            }
            if (ll < rr) {
                swap(data, ll, rr);
                ll++;
            }
            //如果左边的数比基准数大就换位
            while (ll < rr && data[ll] <= base) {
                ll++;
            }
            if (ll < rr) {
                swap(data, rr, ll);
                rr--;
            }
        }
        System.out.println(Arrays.toString(data));
        if (ll > left) {
            fastSort(data, left, ll - 1);
        }
        if (rr < right) {
            fastSort(data, ll + 1, right);
        }
    }
    
    /**
     * 将数组转成字符串
     *
     * @param arrays
     * @param operator 连接符
     * @return
     */
    public static StringBuilder toString(String[] arrays, String operator) {
        StringBuilder result = new StringBuilder();
        //到最后一个直接返回
        for (int i = 0; ; i++) {
            result.append(arrays[i]);
            if (i >= arrays.length - 1) {
                return result;
            }
            result.append(operator);
        }
    }
}
