package com.xl.xml;

import com.xl.util.ResourceUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlTest {
    InputStream inputStream;

    @Before
    public void init() throws UnsupportedEncodingException {
        inputStream = ResourceUtil.getResourceInputStream("xml\\User.xml");
    }

    @Test
    public void test() throws ParserConfigurationException, IOException, SAXException {
        long lasting = System.currentTimeMillis();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        org.w3c.dom.Document doc = builder.parse(inputStream);
        NodeList n1 = doc.getElementsByTagName("name");
        for (int i = 0; i < n1.getLength(); i++) {
            System.out.println(doc.getElementsByTagName("psw"));
        }
    }
}
