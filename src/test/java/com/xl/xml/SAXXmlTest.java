package com.xl.xml;

import com.xl.xml.sax.TagValueHandler;
import org.junit.Test;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/*
 * 事件处理的方式解析XML,事件处理器有程序员编写
 */
public class SAXXmlTest {
    //实现ContentHandler接口处理器
    @Test
    public void read() throws Exception {
        // 1.创建解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // 2.得到解析器
        SAXParser sp = factory.newSAXParser();
        // 3.得到读取器
        XMLReader reader = sp.getXMLReader();
        // 4.设置内容处理器
        TagValueHandler handler = new TagValueHandler();
        reader.setContentHandler(handler);
        // 5.读取xml文档内容
        reader.parse("book.xml");
    }
}
