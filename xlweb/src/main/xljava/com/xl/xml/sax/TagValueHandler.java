package com.xl.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 * Created with IntelliJ IDEA. 内容处理器,得到xml文档内容,接口
 *
 * @author: 徐立
 * Date: 2017-11-21
 * Time: 14:44
 * To change this template use File | Settings | File Templates.
 */
public class TagValueHandler implements ContentHandler {
    @Override
    public void setDocumentLocator(Locator locator) {
    }

    // 开始读取
    @Override
    public void startDocument() throws SAXException {
        System.out.println("开始读取");
    }

    @Override
    public void endDocument() throws SAXException {
    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        System.out.println("<" + qName + ">");
        // 有可能抛出空指针异常
        for (int i = 0; atts != null && i < atts.getLength(); i++) {
            String attName = atts.getQName(i);
            String attValue = atts.getValue(i);
            System.out.println(attName + "=" + attValue);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("结束了!");
    }

    // 读取
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // 打印所有的文档内容
        System.out.println(new String(ch));
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {
    }

    @Override
    public void skippedEntity(String name) throws SAXException {
    }
}