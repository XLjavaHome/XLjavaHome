package com.xl.base;

import org.junit.Test;
import org.seimicrawler.xpath.exception.XpathSyntaxErrorException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-04-24
 * @Time: 11:40
 * To change this template use File | Settings | File Templates.
 */
public class JsoupXpathTest {
    @Test
    public void demoTest() throws XpathSyntaxErrorException {
        String xpath = "//div[@id='post_list']/div[./div/div/span[@class='article_view']/a/num()>1000]/div/h3/allText()";
        String doc = "...";
   /*     JXDocument jxDocument = new JXDocument(doc);
        List rs = jxDocument.sel(xpath);
        for (Object o : rs) {
            if (o instanceof Element) {
                int index = ((Element) o).siblingIndex();
                System.out.println(index);
            }
            System.out.println(o.toString());
        }*/
    }
}
