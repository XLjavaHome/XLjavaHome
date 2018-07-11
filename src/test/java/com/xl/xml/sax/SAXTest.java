package com.xl.xml.sax;

import com.xl.util.ResourceUtil;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.UnsupportedEncodingException;

public class SAXTest {
    private String xmlPath;

    @Before
    public void init() throws UnsupportedEncodingException {
        xmlPath = ResourceUtil.getResourceFile("xml\\book.xml").getPath();
        System.out.println(xmlPath);
    }

    @Test
    public void read() throws Exception {
        // 1.创建解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // 2.得到解析器
        SAXParser sp = factory.newSAXParser();
        // 3.得到读取器
        XMLReader reader = sp.getXMLReader();
        // 4.设置内容处理器
        SaxListHandler handler = new SaxListHandler();
        reader.setContentHandler(handler);
        // 5.读取xml文档内容
        reader.parse(xmlPath);
    }
}

