package com.xl.base;
/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-03-22
 * @Time: 18:07
 * To change this template use File | Settings | File Templates.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Cleanup;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.log4j.Log4j;
import lombok.val;
import org.junit.Test;

@Data
@Log4j
//@NoArgsConstructor
//@AllArgsConstructor
@Value
public class LomBokTest {
    public static String str = "测试";

    /**
     * 不为空
     *
     * @param string
     */
    public void notNullExample(@NonNull String string) {
        System.out.println(string.length());
    }

    /*    @EqualsAndHashCode：实现equals()方法和hashCode()方法
        @ToString：实现toString()方法
        @Cleanup：关闭流
        @Synchronized：对象同步
        @SneakyThrows：抛出异常*/
    @Test
    public void demoTest() {
        val sets = new HashSet<String>();
        val lists = new ArrayList<String>();
        val maps = new HashMap<String, String>();
        //=>相当于如下
        final Set<String> sets2 = new HashSet<>();
        final List<String> lists2 = new ArrayList<>();
        final Map<String, String> maps2 = new HashMap<>();
    }

    public static void main(String[] args) {
        try {
            @Cleanup InputStream inputStream = new FileInputStream(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //=>相当于
        InputStream inputStream_2 = null;
        try {
            inputStream_2 = new FileInputStream(args[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream_2 != null) {
                try {
                    inputStream_2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
