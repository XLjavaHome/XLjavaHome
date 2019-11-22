package com.xl.base;

import com.xl.entity.Student;
import com.xl.util.FileUtil;
import java.io.*;
import java.util.Objects;
import org.junit.Test;

/**
 * Created with 徐立. 1.序列化必须第一行加上outputStream.defaultWriteObject();
 * 2.被transient修饰可以在writeObject里面再写入
 * 3.如果readObject中没有赋值写入也没有用处
 * 4.在readObject 光读没有赋值也没用
 * 5.读写int类型可以多个但是要保持顺序一致
 *
 * @author 徐立
 * @date 2019-05-12
 * @time 23:01
 * To change this template use File | Settings | File Templates.
 */
public class ObjectTest {
    @Test
    public void name() {
        String s = "1212";
        //如果为空直接报异常
        System.out.println(Objects.requireNonNull(s));
    }
    
    @Test
    public void getComponentType() {
        String s = "测试";
        Class<?> componentType = s.getClass().getComponentType();
        System.out.println(componentType);
    }
    
    @Test
    public void write() {
        Student student = new Student();
        student.setName("张三");
        student.setAge(30);
        student.setId(3);
        File tempFile = FileUtil.getTempFile();
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(tempFile))) {
            objectOutputStream.writeObject(student);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(tempFile))) {
            Object o = objectInputStream.readObject();
            System.out.println(o.getClass());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
