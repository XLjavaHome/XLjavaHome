package trans;

import java.util.HashMap;
import java.util.Map;

/**
 * 翻译基类
 *
 * @author Created by jz on 2017/10/24 14:46
 */
public abstract class AbstractTranslator implements Translator {
    protected Map<Language, String> langMap = new HashMap<>();
    
    @Override
    public final String translation(Language from, Language to, String query) {
        String response = "";
        try {
            response = getResponse(from, to, query);
            return parseString(response);
        } catch (Exception e) {
            return response;
        }
    }
    
    /**
     * 通过URL返回JSON数据
     *
     * @param from
     * @param targ
     * @param query
     * @return
     * @throws Exception
     */
    protected abstract String getResponse(Language from, Language targ, String query) throws Exception;
    
    /**
     * 将json数据翻译
     *
     * @param jsonString
     * @return
     */
    protected abstract String parseString(String jsonString);
}
