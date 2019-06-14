package com.xl.base;

import impl.GoogleTranslator;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-03
 * @time 22:51
 * To change this template use File | Settings | File Templates.
 */
public class GoogleTranslationTest {
    @Test
    public void name() {
        // TODO 2019/6/10 23:15 徐立
        GoogleTranslator googleTranslator = new GoogleTranslator();
        String query = "项目经理";
        String name = googleTranslator.translation(query);
        System.out.println(name);
    }
}
