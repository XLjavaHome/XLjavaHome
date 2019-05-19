package com.xl.util;

import lombok.extern.log4j.Log4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.jetbrains.annotations.NotNull;

/**
 * Created with 徐立.汉字转拼音
 *
 * @author 徐立
 * @version 1.0 2019-05-19 22:39
 * To change this template use File | Settings | File Templates.
 * @date 2019-05-19
 * @time 22:39
 */
@Log4j
public class PinYinUtil {
    /**
     * 每个汉字的首字母
     *
     * @param input
     * @return
     */
    public static StringBuilder getSimplePinYin(String input) throws BadHanyuPinyinOutputFormatCombination {
        return getPinYin(input, true);
    }
    
    /**
     * 得到拼音
     *
     * @param input 汉字
     * @param flag true汉字的首字母,false全拼
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    @NotNull
    private static StringBuilder getPinYin(String input, boolean flag) throws BadHanyuPinyinOutputFormatCombination {
        HanyuPinyinOutputFormat pinyinOutputFormat = new HanyuPinyinOutputFormat();
        //全拼
        pinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        //全拼带音调
        pinyinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        StringBuilder pinyin = new StringBuilder();
        String[] pinyinArray = null;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            //字母的话直接加
            //只能转字符
            pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, pinyinOutputFormat);
            //是否有length>1的？有多个重复字母的情况
            if (pinyinArray == null || pinyinArray.length == 0) {
                pinyin.append(c);
                continue;
            }
            String s = pinyinArray[0];
            if (flag) {
                pinyin.append(s, 0, 1);
            } else {
                pinyin.append(s);
            }
        }
        return pinyin;
    }
    
    /**
     * 得到全拼
     *
     * @param input
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public static StringBuilder getFullPinYin(String input) throws BadHanyuPinyinOutputFormatCombination {
        return getPinYin(input, false);
    }
}
