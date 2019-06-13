package trans;

/**
 * 翻译接口
 *
 * @author Created by jz on 2017/10/24 14:46
 */
public interface Translator {
    /**
     * 翻译
     *
     * @param from 从什么语言
     * @param to 翻译到什么语言
     * @param query 翻译的语言
     * @return 翻译后的语言
     */
    String translation(Language from, Language to, String query);
}
