package com.xl;

import com.xl.enumsupport.CharacterEnum;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-07-31
 * @time 18:32
 * To change this template use File | Settings | File Templates.
 */
public class CharacterTest {
    @Test
    void name() {
        System.out.println(CharacterEnum.UTF8.getCharset().name());
    }
}
