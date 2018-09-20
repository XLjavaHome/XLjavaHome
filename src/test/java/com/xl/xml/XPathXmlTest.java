package com.xl.xml;

import com.xl.util.ResourceUtil;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Before;
import org.junit.Test;

//如果xml文档很大.快速定位想要的数据
/*
 "/"表示绝对路径
 "//"表示相对路径
 "*"表示所有由*之前的路径所定位的元素

 */
public class XPathXmlTest {
    InputStream f;

    @Before
    public void before() throws UnsupportedEncodingException {
        f = ResourceUtil.getResourceInputStream("xml/book.xml");
    }

    @Test
    public void test() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(f);
        // select方法调用xpath表达式
        String value = document.selectSingleNode("//作者").getText(); // 获取第一个作者的值
        System.out.println(value);
    }

    /**
     * 查找users.xml是否有何用户匹配的用户名和密码
     *
     * @throws DocumentException
     */
    @Test
    public void find() throws DocumentException, UnsupportedEncodingException {
        String username = "aaa";
        String password = "123";
        // 检测xml文档是否有匹配
        SAXReader reader = new SAXReader();
        f = ResourceUtil.getResourceInputStream("xml/User.xml");
        Document document = reader.read(f);
        // 1:这个b变量要单引号引起来,否则找不到
        // 找出所有的user节点
        Node node = document.selectSingleNode("//user[@username='" + username + "' and @password='" + password + "']"); // 截断字符串
        if (node == null) {
            System.out.println("用户名或密码错误");
        } else {
            System.out.println("登陆成功");
        }
    }
}
