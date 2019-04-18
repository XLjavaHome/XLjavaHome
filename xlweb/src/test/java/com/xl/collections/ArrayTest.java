package com.xl.collections;

import com.xl.util.ArrayUtil;
import com.xl.util.Constant;
import com.xl.util.StringUtil;
import java.util.*;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

/**
 * 数组测试
 */
@Log4j
public class ArrayTest {
    String[] strings = new String[]{"223", "21", "sa"};
    private int[] intArray = new int[]{3, 56, 1, 4, 76721, 343, 1, 43, 764, 9, 765};
    private Integer[] integerArray = new Integer[]{3, 56, 1, 4, 76721, 343, 1};
    
    /**
     * 设要排序的数组是A[0]……A[N-1]，首先任意选取一个数据（通常选用数组的第一个数）作为关键数据，然后将所有比它小的数都放到它前面，所有比它大的数都放到它后面，这个过程称为一趟快速排序。值得注意的是，快速排序不是一种稳定的排序算法，也就是说，多个相同的值的相对位置也许会在算法结束时产生变动
     */
    @Test
    public void fastTest() {
        //left参数固定写0、、
        ArrayUtil.fastSort(intArray, 0, intArray.length - 1);
    }
    
    /**
     * 数组换位测试
     */
    @Test
    public void swapTest() {
        System.out.println(Arrays.toString(intArray));
        ArrayUtil.swap(intArray, 2, 3);
        System.out.println(Arrays.toString(intArray));
    }
    
    @Test
    public void sortTest() {
        Integer[] arr = getIntegersArr();
        //快速排序
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 1;
            }
        });
        System.out.println(Arrays.toString(arr));
    }
    
    @NotNull
    public Integer[] getIntegersArr() {
        return new Integer[]{1123, 12, 11, 13, 1};
    }
    
    @Test
    public void inTest() {
        int[] ints = new int[3001];
        for (int i = 0; 1 < 3001; i++) {
            ints[i] = i;
        }
    }
    
    @Test
    public void testSort() {
        System.out.println(Arrays.toString(intArray));
    }
    
    /**
     * 扩大数组长度10%+10
     */
    @Test
    public void length() {
        int length = intArray.length;
        ArrayUtil.arrayAddLength(intArray, 10);
        System.out.println(intArray.length);
        ArrayUtil.arrayAddLength(intArray, 2);
        System.out.println(intArray.length);
    }
    
    /**
     * 如果为空的话就没有值
     */
    @Test
    public void joinTest() {
        System.out.println(StringUtil.join(strings, Constant.BR));
    }
    
    /**
     * 包含
     */
    @Test
    public void containTest() {
        //true
        System.out.println(ArrayUtils.contains(strings, "21"));
        //不包含
        System.out.println(ArrayUtils.contains(strings, null));
        //false
        System.out.println(ArrayUtils.contains(strings, 21));
        //true
        System.out.println(ArrayUtils.contains(integerArray, 1));
    }
    
    /**
     * 将数组中的每个值都打印出来
     */
    @Test
    public void printTest() {
        System.out.println(Arrays.toString(intArray));
    }
    
    @Test
    public void arrays() {
        Integer[] arr = getIntegersArr();
        List<Integer> list = Arrays.asList(arr);
        System.out.println(list);
        // 数组转换成Set 将数组转换成List后，再用List构造Set
        Set set = new HashSet(list);
        System.out.println(set);
    }
}
