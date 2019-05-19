package com.xl.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
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
    /**
     * 初始化拼音
     */
    public static final HanyuPinyinOutputFormat DEFAULT_FORMAT = new HanyuPinyinOutputFormat();
    
    public PinYinTest() {
        //全拼
        DEFAULT_FORMAT.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        //全拼带音调
        //DEFAULT_FORMAT.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);
        DEFAULT_FORMAT.setVCharType(HanyuPinyinVCharType.WITH_V);
    }
    
    @Test
    public void pinyinTest() throws BadHanyuPinyinOutputFormatCombination {
        String input = "世界你好啊";
        StringBuilder pinyin = new StringBuilder();
        String[] pinyinArray = null;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            //只能转字符
            pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, DEFAULT_FORMAT);
            if (pinyinArray != null) {
                //是否有length>1的？有多个重复字母的情况
                //首字母
                String s = pinyinArray[0];
                pinyin.append(s.substring(0, 1));
            }
        }
        System.out.println(pinyin.toString().trim().toLowerCase());
    }
    
    private String[] 带声调(String[] pinyinArray, char c) {
        pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);
        return pinyinArray;
    }
}
