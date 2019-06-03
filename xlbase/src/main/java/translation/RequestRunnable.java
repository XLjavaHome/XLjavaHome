package translation;

import com.xl.util.StringUtil;
import impl.GoogleTranslator;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import trans.Language;

/**
 * 请求
 *
 * @author Created by jz on 2017/10/24 14:45
 */
public class RequestRunnable implements Callable<String> {
    private GoogleTranslator mGoogleTranslator;
    private String mQuery;
    
    @Override
    public String call() {
        String text;
        if (isChinese(mQuery)) {
            text = mGoogleTranslator.translation(Language.ZH, Language.EN, mQuery);
        } else {
            text = mGoogleTranslator.translation(Language.EN, Language.ZH, mQuery);
        }
        String resultText = clearText(text);
        return resultText;
    }
    
    private boolean isChinese(String strName) {
        char[] cs = strName.toCharArray();
        for (char c : cs) {
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 净化
     *
     * @param text
     * @return
     */
    private String clearText(String text) {
        if (text == null) {
            return null;
        }
        List<String> strings = StringUtil.split(text, " ");
        List<String> newString = new ArrayList<>(strings.size());
        if (strings.size() > 0) {
            for (int i = 0; i < strings.size(); i++) {
                String s = null;
                if (i == 0) {
                    s = strings.get(i).substring(0, 1).toLowerCase() + strings.get(i).substring(1);
                } else {
                    s = strings.get(i).substring(0, 1).toUpperCase() + strings.get(i).substring(1);
                }
                if (i == strings.size() - 1) {
                    s = s.replace("\r", "");
                    s = s.replace("\n", "");
                }
                newString.add(s);
            }
        }
        return StringUtil.join(newString, "");
    }
    
    private boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
               || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
               || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
               || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }
}
