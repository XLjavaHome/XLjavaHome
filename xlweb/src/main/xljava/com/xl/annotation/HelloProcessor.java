package com.xl.annotation;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

@SupportedAnnotationTypes("com.xl.annotation.Igore")
@SupportedSourceVersion(SourceVersion.RELEASE_8) // 源码级别, 这里的环境是 jdk 1.8
//@SupportedAnnotationTypes("com.xl.annotation.Igore") // 处理的注解类型, 这里需要处理的是 apt 包下的 User 注解(这里也可以不用注解, 改成重写父类中对应的两个方法)
public class HelloProcessor extends AbstractProcessor {
    // 计数器, 用于计算 process() 方法运行了几次
    private int count = 1;

    public HelloProcessor() {
    }

    /**
     * 这里必须指定，这个注解处理器是注册给哪个注解的。注意，它的返回值是一个字符串的集合，包含本处理器想要处理的注解类型的合法全称
     *
     * @return 注解器所支持的注解类型集合，如果没有这样的类型，则返回一个空集合
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotataions = new LinkedHashSet<String>();
        annotataions.add(HelloProcessor.class.getCanonicalName());
        return annotataions;
    }

    /**
     * 指定使用的Java版本，通常这里返回SourceVersion.latestSupported()，默认返回SourceVersion.RELEASE_6
     *
     * @return 使用的Java版本
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        System.out.println("注解初始化");
    }

    // 处理编译时注解的方法
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("start process, count = " + count++);
        // 获得所有类
        Set<? extends Element> rootElements = roundEnv.getRootElements();
        System.out.println("all class:");
        for (Element rootElement : rootElements) {
            System.out.println("  " + rootElement.getSimpleName());
        }
        // 获得有注解的元素, 这里 User 只能修饰类, 所以只有类
        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(Igore.class);
        System.out.println("annotated class:");
        for (Element element : elementsAnnotatedWith) {
            String className = element.getSimpleName().toString();
            System.out.println("  " + className);
            String output = element.getAnnotation(Igore.class).name();
            // 产生的动态类的名字
            String newClassName = className + "_New";
        }
        return true;
    }
}