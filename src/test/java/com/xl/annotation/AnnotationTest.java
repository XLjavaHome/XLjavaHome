package com.xl.annotation;

import java.io.FileNotFoundException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 徐立
 * Date: 2017/10/16
 * Time: 15:12
 * To change this template use File | Settings | File Templates.
 */
public class AnnotationTest {
    @SuppressWarnings({"unchecked", "deprecation"})
    @MethodInfo(author = "徐立", comments = "Main method", date = "Nov 17 2012", revision = 10)
    public static void genericsTest() throws FileNotFoundException {
        List l = new ArrayList();
        l.add("abc");
        oldMethod();
    }

    @Deprecated
    @MethodInfo(comments = "deprecated method", date = "Nov 17 2012")
    public static void oldMethod() {
        System.out.println("过去的方法弃用");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        for (Method method : AnnotationTest.class.getClassLoader().loadClass(("com.xl.annotation.AnnotationTest")).getMethods()) {
            if (method.isAnnotationPresent(MethodInfo.class)) {
                for (Annotation anno : method.getDeclaredAnnotations()) {
                    System.out.println("Annotation in Method '" + method + "' : " + anno);
                }
                MethodInfo methodAnno = method.getAnnotation(MethodInfo.class);
                if (methodAnno.revision() == 1) {
                    System.out.println("Method with revision no 1 = " + method);
                }
            }
        }
    }

    @Override
    @MethodInfo(author = "徐立", comments = "Main method", date = "Nov 17 2012", revision = 1)
    public String toString() {
        return "这是toString方法";
    }
}
