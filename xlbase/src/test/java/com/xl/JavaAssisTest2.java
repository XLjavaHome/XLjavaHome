package com.xl;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import javassist.*;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-26
 * @time 18:06
 * To change this template use File | Settings | File Templates.
 */
public class JavaAssisTest2 {
    @Test
    void name() throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException, CannotCompileException, NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        String classname = "com.xl.JavaAssisDemo";
        CtClass clazz1 = pool.makeClass(classname);
        // add an int field
        CtField field1 = new CtField(CtClass.intType, "id", clazz1);
        field1.setModifiers(Modifier.PRIVATE);
        clazz1.addField(field1);
        // add a String field
        CtField field2 = new CtField(pool.get("java.lang.String"), "name", clazz1);
        field2.setModifiers(Modifier.PROTECTED);
        clazz1.addField(field2);
        // create a constructor
        CtConstructor con = new CtConstructor(new CtClass[]{CtClass.intType, pool.get("java.lang.String")}, clazz1);
        con.setBody("{this.id=$1; this.name=$2;}");
        clazz1.addConstructor(con);
        // create a method
        CtMethod method = CtNewMethod.make("public void test(){}", clazz1);
        method.insertBefore("System.out.println(id + \" \" + name);\n" + "System.out.println(name.toString());\n"
                            + "com.xl.entity.Student s=new com.xl.entity.Student();\n" + "s.setName(name);\n");
        //添加异常
        CtClass etype = ClassPool.getDefault().get("java.lang.Exception");
        method.addCatch("{System.out.println(id + \" \" + name);}throw $e;", etype);
        clazz1.addMethod(method);
        CtClass clazz = clazz1;
        clazz.writeFile("output");
        URLClassLoader loader = new URLClassLoader(
                new URL[]{new URL("file:" + "output" + "/")});  // must put a "/" at last to specify a dir
        Class<?> clazz2 = loader.loadClass(classname);
        Constructor<?> con1 = clazz2.getDeclaredConstructor(int.class, String.class);
        Object obj = con1.newInstance(24, "李雷");  // call a constructor
        clazz2.getMethod("test").invoke(obj);  // call a method
        loader.close();
    }
}
