package com.xl;

import com.xl.enumsupport.SexEnum;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-12-02
 * @time 23:46
 * To change this template use File | Settings | File Templates.
 */
public class SwitchTest {
    @Test
    void nullTest() {
        Integer a = null;
        switch (a) {
            case 1:
                System.out.println("1111   ");
                break;
        }
    }
    
    @Test
    void enumTest() {
        SexEnum man = SexEnum.man;
        switch (man) {
            case man:
                System.out.println("这是男人");
                break;
            case woman:
                System.out.println("这是女人");
                break;
        }
    }
}
