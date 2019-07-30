package com.xl.translator;

import com.xl.util.StringUtil;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;

/**
 * 翻译基类
 *
 * @author Created by jz on 2017/10/24 14:46
 */
public abstract class AbstractTranslator implements Translator {
    protected Map<Language, String> langMap;
    
    /**
     * 获取翻译的URL
     *
     * @return
     */
    protected abstract String getTranslatorUrl();
    
    /**
     * 将字符串翻译  中英或英中
     *
     * @param mQuery
     * @return
     */
    public final String translation(String mQuery) {
        boolean isChinese = StringUtil.isChinese(mQuery);
        String result = isChinese ? translation(Language.ZH, Language.EN, mQuery) : translation(Language.EN, Language.ZH, mQuery);
        //中文翻译英文要经过处理
        if (isChinese) {
            // 2019/7/30 11:45 徐立 去掉结尾问号
            String[] split = StringUtils.split(result, " ");
            String collect = Stream.of(split).skip(1).map(s -> s.substring(0, 1).toUpperCase() + s.substring(1)
                                                                                                  .replaceAll("\\p{P}", ""))
                                   .collect(Collectors.joining());
            return split[0].toLowerCase() + collect;
        }
        return result;
    }
    
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
