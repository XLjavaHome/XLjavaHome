package com.xl.base;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-05-30
 * @time 23:19
 * To change this template use File | Settings | File Templates.
 */
public class TryTest {
    @Test
    public void name() {
        //try括号 会关闭资源不用在finally关闭
        try (FileOutputStream fileOutputStream = new FileOutputStream("D:\1")) {
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
