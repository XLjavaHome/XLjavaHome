package translation;

import com.xl.translator.impl.GoogleTranslator;
import com.xl.util.StringUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

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
        String text = mGoogleTranslator.translation(mQuery);
        String resultText = clearText(text);
        return resultText;
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
}
