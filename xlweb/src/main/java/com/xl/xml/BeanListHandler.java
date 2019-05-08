package com.xl.xml;

import com.xl.entity.Book;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created with IntelliJ IDEA. 把XML文档中的每一本书封装到一个book对象,并把多个book对象放在一个list集合中
 *
 * @author: 徐立
 * Date: 2017-11-21
 * Time: 14:52
 * To change this template use File | Settings | File Templates.
 */
public class BeanListHandler extends DefaultHandler {
    private List list = new ArrayList();
    private String currentTag;
    private Book book;

    @Override
    public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
        currentTag = name;
        if ("书".equals(currentTag)) {
            book = new Book();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // 当结束标签为书的时候说明全部存到了book对象,这个时候存进List
        if ("书".equals(qName)) {
            list.add(book);
            // book=null; //这个可以去掉
        }
        // 如果currentTag不等于null会出空指针异常,
        currentTag = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if ("书名".equals(currentTag)) {
            String name = new String(ch, start, length);
            book.setName(name);
        }
        if ("作者".equals(currentTag)) {
            String author = new String(ch, start, length);
            book.setAuthor(author);
        }
        if ("售价".equals(currentTag)) {
            String price = new String(ch, start, length);
            book.setPrice(price);
        }
    }

    public List getBooks() {
        return list;
    }
}
