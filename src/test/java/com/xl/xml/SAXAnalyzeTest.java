package com.xl.xml;

import com.xl.entity.Book;
import org.junit.Test;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.Iterator;
import java.util.List;

public class SAXAnalyzeTest {
    @Test
    public void read() throws Exception {
        // 1.创建解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // 2.得到解析器
        SAXParser sp = factory.newSAXParser();
        // 3.得到读取器
        XMLReader reader = sp.getXMLReader();
        // 4.设置内容处理器
        BeanListHandler handler = new BeanListHandler();
        reader.setContentHandler(handler);
        // 5.读取xml文档内容
        reader.parse("book.xml");
        List list = handler.getBooks();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Book book = (Book) iterator.next();
            System.out.println(book.getName() + "" + book.getAuthor() + ".." + book.getPrice());
        }
    }
}
