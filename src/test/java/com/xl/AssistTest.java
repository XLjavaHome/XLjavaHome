package com.xl;

import com.xl.entity.User;
import java.io.IOException;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.NotFoundException;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author: 徐立
 * @Date: 2019-01-07
 * @Time: 22:38
 * To change this template use File | Settings | File Templates.
 */
public class AssistTest {
    private static final String className = User.class.getName();
    
    public static void main(String args[]) throws NotFoundException, CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
        //设置classPath
        pool.appendClassPath("D:\\code\\XLjavaHome\\target\\classes");
        //获取一个class文件
        CtClass ctClass = pool.get(className);
        //获取构造方法
        CtConstructor ctConstructor = ctClass.getConstructors()[0];
        //插入输出语句
        ctConstructor.insertAfter("System.out.println();");
        ctClass.writeFile("D:\\output");
        System.out.println("OK");
    }
    
    /**
     * 测试
     */
    @Test
    public void demoTest() throws NotFoundException, CannotCompileException {
        ClassPool pool = ClassPool.getDefault();
        //定义类
        CtClass stuClass = pool.makeClass("com.ricky.Student");
        CtClass cc = pool.get("java.lang.String");
        CtField idField = new CtField(CtClass.longType, "id", stuClass);
        stuClass.addField(idField);
    }
}
