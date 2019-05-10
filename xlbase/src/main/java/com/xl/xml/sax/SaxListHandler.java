package com.xl.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 徐立
 * Date: 2017-11-21
 * Time: 14:41
 * To change this template use File | Settings | File Templates.
 */
// 继承DefaultHandler,这个解析器是按顺序读的,要覆盖3个方法
class SaxListHandler extends DefaultHandler {
    private String currentTag; // 记录当前解析到的是什么标签
    private int needNumber = 1; // 记录想获取的第几个作者标签的值
    private int currentNumber; // 当前解析到第几个

    @Override
    public void startElement(String uri, String localName, String name, Attributes attributes) {
        // 将标签名赋值给currentTag
        currentTag = name;
        // 当解析到第一个作者的时候currentNumber++
        if ("作者".equals(currentTag)) {
            currentNumber++;
        }
    }

    @Override
    public void endElement(String uri, String localName, String name) {
        // 当读到结束标签的时候置为空
        currentTag = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if ("作者".equals(currentTag) && currentNumber == needNumber) {
            // 这就是标签里面的内容
            System.out.println(new String(ch, start, length));
        }
    }
}
