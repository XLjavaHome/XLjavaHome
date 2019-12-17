package com.xl;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.不可变的集合
 *
 * @author 徐立
 * @date 2019-12-17
 * @time 18:07
 * To change this template use File | Settings | File Templates.
 */
public class ImmutableListTest {
    @Test
    void create1() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        ImmutableList<String> imlist = ImmutableList.copyOf(list);
        System.out.println("imlist：" + imlist);
    }
    
    @Test
    void create2() {
        ImmutableList<String> immutableList = ImmutableList.of("peida", "jerry", "harry");
        System.out.println("imOflist：" + immutableList);
    }
    
    /**
     * 用builder
     */
    @Test
    void create3() {
        //关键看这里是否imlist也添加新元素了
        ImmutableSet<Color> imColorSet =
                ImmutableSet.<Color>builder()
                        .add(new Color(0, 255, 255))
                        .add(new Color(0, 191, 255))
                        .build();
        System.out.println("imColorSet:" + imColorSet);
    }
}
