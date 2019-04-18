package com.xl.base;

import com.xl.entity.User;
import com.xl.util.FileUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA. 反序列化测试
 *
 * @author: 徐立
 * @Date: 2018-03-26
 * @Time: 18:08
 * To change this template use File | Settings | File Templates.
 */
public class TransientTest {
    @Test
    public void demoTest() throws IOException, ClassNotFoundException {
        User userInfo = new User("张三", "123456");
        System.out.println(userInfo);
        // 序列化，被设置为transient的属性没有被序列化
        String fileName = "UserInfo.txt";
        File tempFile = FileUtil.createTempFile(fileName);
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(tempFile));
        o.writeObject(userInfo);
        o.close();
        //在反序列化之前改变name的值
        userInfo.setName("测试");
        // 重新读取内容,password不会读出来
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(tempFile));
        userInfo = (User) in.readObject();
        in.close();
        //读取后psw的内容为null
        System.out.println(userInfo.toString());
    }
}
