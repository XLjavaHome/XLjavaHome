package com.xl.xml;

import com.xl.util.ResourceUtil;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class JaxpXmlTest {
    private InputStream inputStream;

    @Before
    public void before() throws UnsupportedEncodingException {
        inputStream = ResourceUtil.getResourceInputStream("xml/book.xml");
    }

    @Test
    public void read() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(inputStream);
        NodeList list = document.getElementsByTagName("书名");
        // getLength获取列表节点数
        System.out.println(list.getLength());
        // 获取节点
        Node node = list.item(1);
        // 获取节点中的内容
        String content = node.getTextContent();
        System.out.println(content);
    }

    // 得到所有的标签
    @Test
    public void read2() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(inputStream);
        // 得到根节点
        Node root = document.getElementsByTagName("书架").item(0);
        list(root);
    }

    // 得到xml中标签的属性值:<书名 name="xxx">
    @Test
    public void read3() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("xml/book.xml");
        // Element是Node的子类,强转
        Element bookName = (Element) document.getElementsByTagName("书名").item(0);
        String value = bookName.getAttribute("name");
        System.out.println(value);
    }

    // 向xml文档添加节点:<售价>59.00元</售价>
    @Test
    public void add() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("xml/book.xml");
        // 创建节点
        Element price = document.createElement("售价");
        price.setTextContent("59.00元");
        // 把创建的节点挂到第一本书上
        Element book = (Element) document.getElementsByTagName("书").item(0);
        //		book.appendChild(price);
        // 把更新后内存写到xml文档
        TransformerFactory tsf = TransformerFactory.newInstance();
        Transformer tf = tsf.newTransformer();
        //		tf.transform(new DOMSource(document), new StreamResult(
        //				new FileOutputStream("xml/book.xml")));
        // 向指定位置添加节点,向售价节点之前插
        // 得到参考节点refChild
        Element refNode = (Element) document.getElementsByTagName("售价").item(0);
        // 得要挂载的节点(父节点)book
        // 往book节点的指定位置插入
        book.insertBefore(price, refNode);
        tf.transform(new DOMSource(document), new StreamResult(new FileOutputStream("xml/book.xml")));
        System.out.println("文件更新成功!");
    }

    // 向xml文档添加name属性:<书名 name="xxx">59.00元</书名>
    @Test
    public void add2() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("xml/book.xml");
        Element bookname = (Element) document.getElementsByTagName("书名").item(0);
        bookname.setAttribute("name", "xxxx");
        TransformerFactory tsf = TransformerFactory.newInstance();
        Transformer tf = tsf.newTransformer();
        tf.transform(new DOMSource(document), new StreamResult(new FileOutputStream("xml/book.xml")));
        System.out.println("文件更新成功!");
    }

    //删除节点
    @Test
    public void delete() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("xml/book.xml");
        //得到删除的节点
        Element e = (Element) document.getElementsByTagName("售价").item(0);
        //		得到要删除节点的爸爸
        //		1.用得到父节点
        Element book = (Element) e.getParentNode();
        book.removeChild(e);
        TransformerFactory tsf = TransformerFactory.newInstance();
        Transformer tf = tsf.newTransformer();
        tf.transform(new DOMSource(document), new StreamResult(new FileOutputStream("xml/book.xml")));
        System.out.println("文件更新成功!");
        //简写:   e.getParentNode().removeChild(e);
        //删除父节点 e.getParentNode().getParentNode().removeChild(e.getParentNode());
    }

    // 打印节点
    private void list(Node node) {
        // 判断如果node是标签.就打印是文本就什么也不错
        if (node instanceof Element) {
            System.out.println(node.getNodeName());
        }
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node child = list.item(i);
            list(child);
        }
    }
}
