package com.xl;

import com.xl.util.FileUtil;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javassist.*;
import javassist.bytecode.AccessFlag;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-19
 * @time 17:51
 * To change this template use File | Settings | File Templates.
 */
public class JavaAssistTest {
    private String packgeName = "com.xl";
    private File tempDrectory;
    private String classname = packgeName + ".GenerateClass";
    
    @Test
    void name() throws IOException, CannotCompileException, NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        CtClass ct = pool.makeClass(classname);//创建类
        ct.setInterfaces(new CtClass[]{pool.makeInterface("java.lang.Cloneable")});//让类实现Cloneable接口
        CtField f = new CtField(CtClass.intType, "id", ct);//获得一个类型为int，名称为id的字段
        f.setModifiers(AccessFlag.PUBLIC);//将字段设置为public
        ct.addField(f);//将字段设置到类上
        //添加构造函数
        CtConstructor constructor = CtNewConstructor.make("public GeneratedClass(int pId){this.id=pId;}", ct);
        ct.addConstructor(constructor);
        //添加方法
        CtMethod helloM = CtNewMethod.make("public void hello(String des){ System.out.println(des);}", ct);
        ct.addMethod(helloM);
        //将生成的.class文件保存到磁盘
        tempDrectory = FileUtil.getTempDrectory();
        ct.writeFile(tempDrectory.getAbsolutePath());
        //下面的代码为验证代码
        Field[] fields = ct.toClass().getFields();
        System.out.println("属性名称：" + fields[0].getName() + "  属性类型：" + fields[0].getType());
    }
    
    @Test
    public void modifyMethod() throws NoSuchMethodException, NotFoundException, IOException, CannotCompileException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ClassPool pool = ClassPool.getDefault();
        CtClass ct = pool.getCtClass(classname);
        CtMethod m = ct.getDeclaredMethod("move");
        m.insertBefore("{ System.out.print(\"dx:\"+$1); System.out.println(\"dy:\"+$2);}");
        m.insertAfter("{System.out.println(this.x); System.out.println(this.y);}");
        ct.writeFile();
        //通过反射调用方法，查看结果
        Class pc = ct.toClass();
        Method move = pc.getMethod("move", int.class, int.class);
        Constructor<?> con = pc.getConstructor(int.class, int.class);
        move.invoke(con.newInstance(1, 2), 1, 2);
    }
}
