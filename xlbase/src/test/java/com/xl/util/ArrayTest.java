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
        String[] arr = new String[]{"张三", "李四", "王五"};
        String str = "李四1";
        int index = ArrayUtils.indexOf(arr, str);
        if (index > -1) {
            String[] remove = ArrayUtils.remove(arr, index);
            Stream.of(remove).forEach(System.out::println);
        }
    }
}
