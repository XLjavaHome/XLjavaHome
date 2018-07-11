package com.xl.xml;

import com.xl.util.ResourceUtil;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class XmlTest {
    File file;

    @Before
    public void init() throws UnsupportedEncodingException {
        file = ResourceUtil.getResourceFile("xml\\User.xml");
    }

    @Test
    public void test() throws ParserConfigurationException, IOException, SAXException {
        long lasting = System.currentTimeMillis();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        org.w3c.dom.Document doc = builder.parse(file);
        NodeList n1 = doc.getElementsByTagName("name");
        for (int i = 0; i < n1.getLength(); i++) {
            System.out.println(doc.getElementsByTagName("psw"));
        }
    }
}
