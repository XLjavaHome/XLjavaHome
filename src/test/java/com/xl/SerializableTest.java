package com.xl;

import com.xl.entity.Book;
import com.xl.util.FileUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.junit.Test;

/**
 * Created with 徐立.序列化测试
 *
 * @author: 徐立
 * @Date: 2018-10-27
 * @Time: 22:00
 * To change this template use File | Settings | File Templates.
 */
public class SerializableTest {
    @Test
    public void demoTest() throws IOException, ClassNotFoundException {
        Book b = new Book();
        b.setName("heloWorld");
        b.setAuthor("heloWorld");
        b.setPrice("heloWorld");
        b.setHasRight(false);
        File desktopFile = FileUtil.getDesktopFile("1.txt");
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(desktopFile));
        os.writeObject(b);
        os.close();
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(desktopFile));
        Object o = is.readObject();
        System.out.println(o);
        is.close();
    }
}
