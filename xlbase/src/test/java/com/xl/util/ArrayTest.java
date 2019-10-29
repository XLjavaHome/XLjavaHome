package com.xl.util;

import java.util.stream.Stream;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-05-20
 * @time 13:39
 * To change this template use File | Settings | File Templates.
 */
public class ArrayTest {
    private String[] arr = new String[]{"张三", "李四", "王五"};
    
    @Test
    void 数组change() {
        //初始化数组，改变他的引用之后，原数组是否也发生改变: 会发生改变，如果是clone就不影响原数组
        String[] arr = initArray();
        String[] arr2 = arr.clone();
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = arr2[i] + i;
        }
        Stream.of(arr).forEach(System.out::println);
    }
    
    private String[] initArray() {
        return new String[]{"测试", "测试"};
    }
    
    @Test
    public void 包含测试() {
        Integer[] arr = new Integer[]{2, 1111, 3232};
        System.out.println(ArrayUtils.contains(arr, 1111));
        System.out.println(ArrayUtils.contains(arr, 222));
    }
    
    /**
     * 数组的删除
     */
    @Test
    void removeTest() {
        String str = "李四1";
        int index = ArrayUtils.indexOf(arr, str);
        if (index > -1) {
            String[] remove = ArrayUtils.remove(arr, index);
            Stream.of(remove).forEach(System.out::println);
        }
    }
    
    /**
     * 复制测试
     */
    @Test
    void copyTest() {
        String[] arr2 = new String[arr.length];
        System.arraycopy(arr, 1, arr2, 1, 2);
        ArrayUtil.print(arr2);
    }
    
    @Test
    void printTest() {
        ArrayUtil.print(arr);
    }
    
    @Test
    void splitTest() {
        Object[] subarray = org.apache.commons.lang.ArrayUtils.subarray(arr, 0, 2);
        ArrayUtil.print(subarray);
        ArrayUtil.print(arr);
    }
    
    @Test
    void 除() {
        int length = 23;
        Integer[] arr = new Integer[length];
        for (int j = 0; j < arr.length; j++) {
            arr[j] = j;
        }
        int index = 5;
        splitArray(arr, index);
        ArrayUtil.print(arr);
    }
    
    private void splitArray(Integer[] arr, int index) {
        int i = arr.length / index;
        for (int x = 0; x < i - 1; x++) {
            int startIndexInclusive = x * index;
            Object[] subarray1 = org.apache.commons.lang.ArrayUtils.subarray(arr, startIndexInclusive,
                                                                             startIndexInclusive + index);
            ArrayUtil.print(subarray1);
        }
        Object[] subarray = org.apache.commons.lang.ArrayUtils.subarray(arr, i * index, arr.length);
        ArrayUtil.print(subarray);
    }
}
