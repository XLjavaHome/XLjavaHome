package com.xl.util;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.extern.log4j.Log4j;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-05-20
 * @time 13:39
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class ArrayTest {
    private String[] arr = new String[]{"张三", "李四", "王五"};
    
    /**
     * todo 将String数组转成Integer数组
     */
    @Test
    void intArray() {
        StopWatch stopWatch = new StopWatch();
        int endExclusive = 1000000;
        String[] stringArr=new String[endExclusive];
        int[] ints1 = IntStream.rangeClosed(1, endExclusive).toArray();
        for (int i = 0; i < ints1.length; i++) {
            stringArr[i] = ints1[i] + "";
        }
        stopWatch.start();
        Integer[] convert = (Integer[]) ConvertUtils.convert(stringArr, Integer.class);
        stopWatch.stop();
        System.out.println(stopWatch);
        stopWatch.start();
        //效率最高
        int[] ints = ArrayUtil.toIntArray(stringArr);
        stopWatch.stop();
        System.out.println(Arrays.toString(ints));
        System.out.println(stopWatch);
        stopWatch.start();
        Integer[] integers = ArrayUtil.toIntArray2(stringArr);
        stopWatch.stop();
        System.out.println(stopWatch);
    }
    
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
        String[] arr2 = new String[10];
        System.arraycopy(arr, 1, arr2, 1, 2);
        ArrayUtil.print(arr2);
        System.out.println("----------");
        ArrayUtil.print(Arrays.copyOf(arr2, 3));
    }
    
    @Test
    void printTest() {
        IntStream.range(1, 100).filter(x -> x % 2 == 1).forEach(System.out::println);
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
