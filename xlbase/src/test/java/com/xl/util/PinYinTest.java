package com.xl.util;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-05-19
 * @time 18:38
 * To change this template use File | Settings | File Templates.
 */
public class PinYinTest {
    @Test
    public void pinyinTest() throws BadHanyuPinyinOutputFormatCombination {
        String input = "世界ces你好啊";
        ClassLoaderTest.log.info(PinYinUtil.getSimplePinYin(input));
        ClassLoaderTest.log.info(PinYinUtil.getFullPinYin(input));
    }
}
