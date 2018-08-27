package com.xl.collections;

import com.xl.util.ArrayUtil;
import com.xl.util.Constant;
import com.xl.util.StringUtil;
import java.util.Arrays;
import java.util.Comparator;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;

@Log4j
public class ArraysTest {
    String[] strings = new String[]{"223", "21", "sa"};
    private int[] intArray = new int[]{3, 56, 1, 4, 76721, 343, 1};

    @Test
    public void sortTest() {
        // int[] arr = {2,4,5};
        // Print.print(Arrays.toString(arr));
        Integer[] arr = {1123, 12, 11, 13, 1};
        // List<int[]> list = Arrays.asList(arr); // 输出的是地址 写int会出错
        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 1;
            }
        });
        System.out.println(Arrays.toString(arr));
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
        //false
        System.out.println(ArrayUtils.contains(strings, null));
        //false
        System.out.println(ArrayUtils.contains(strings, 21));
    }

    /**
     * 将数组中的每个值都打印出来
     */
    @Test
    public void printTest() {
        System.out.println(Arrays.toString(intArray));
    }
}
