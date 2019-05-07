package com.xl.reflect;

import com.xl.base.LogTest;
import com.xl.entity.PageLoadContext;
import com.xl.entity.Person;
import com.xl.entity.Student;
import com.xl.util.Constant;
import com.xl.util.ReflectUtil;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.JDBCType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.ResolvableType;

/**
 * Created with 徐立.getFields：获取所有public的成员变量。
 * <p>
 * getDeclaredFields ：获取所有成员变量。
 *
 * @uthor: 徐立
 * @Date: 2018-11-30
 * @Time: 22:28
 * To change this template use File | Settings | File Templates.
 */
public class ReflectTest {
    Person p = new Person();
    private String className = null;
    private Student student;
    
    @Before
    public void before() {
        className = "com.xl.web.action.IndexAction";
    }
    
    @Test
    public void 获取所有属性值() {
        p.setAge(10);
        p.setName("张三");
        ReflectUtil.getPropertyGeneric(p);
    }
    
    @Test
    public void name() {
        System.out.println(ReflectUtil.getClassGenericType(JDBCType.class, 1));
    }
    
    /**
     * 反射获取类型
     *
     * @throws NoSuchFieldException
     */
    @Test
    public void example() throws NoSuchFieldException {
        ResolvableType t = ResolvableType.forField(getClass().getDeclaredField("student"));
        System.out.println(t);
        t.getSuperType(); // AbstractMap<Integer, List<String>>
        t.asMap(); // Map<Integer, List<String>>
        t.getGeneric(0).resolve(); // Integer
        t.getGeneric(1).resolve(); // List
        t.getGeneric(1); // List<String>
        t.resolveGeneric(1, 0); // String
    }
    
    /**
     * 一个类执行接口中的方法
     */
    @Test
    public void moethodInvokeTest() throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
    }
    
    /*
     * 获取指定Class中的公有方法。
     */
    @Test
    public void getMethodDemo() throws ClassNotFoundException, SecurityException, IllegalArgumentException {
        Class<?> clazz = Class.forName(className);
        Method[] methods = clazz.getMethods(); // 获取的都是公有的
        // 第一个参数是方法的名字，后面的参数是方法的参数类型
        // Method method = clazz.getMethod("aa1", String.class, int.class);
        // 运行这个方法
        // method.invoke(p, "sa", 36);
        for (Method amethods : methods) {
            // 打印公用的方法全名
            System.out.println(amethods.getName());
        }
        // 只获取私有的方法
        methods = clazz.getDeclaredMethods();
        for (Method bmethods : methods) {
            System.out.println(bmethods);
        }
    }
    
    /**
     * 无参的方法
     */
    @Test
    public void getMethodDemo_2() throws Exception {
        Class<?> clazz = Class.forName("reflect.Person");
        Method method = clazz.getMethod("show", null); // 获取空参数的一般方法。
        // Object obj=clazz.newInstance(); //建立Person对象
        // method.invoke(obj,null); //直接运行了show方法，如果是私有的运行不了
        Constructor constructor = clazz.getConstructor(String.class, int.class);
        Object obj = constructor.newInstance("小明", 30);
    }
    
    // 静态方法
    @Test
    public void getMethodDemo_3() throws Exception {
        Class<?> clazz = Class.forName(className);
        Method method = clazz.getMethod("aa1", int.class);// 方法名+参数列表
        // 因为是静态的所以不需要对象
        method.invoke(null, 23); // 运行
    }
    
    // main方法,抛出一个错误参数异常
    @Test
    public void test1() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException, ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        Method method = clazz.getMethod("callstored", String[].class);
        // 1.方法一：
        method.invoke(null, new Object[]{new String[]{"aa", "bb"}}); // jdk1.4没有String[],
        // 在这两个参数，升级过程不兼容导致,所以必须加Object
        // 2.方法二：不让他猜就行，这不是数组
        method.invoke(null, (Object) new String[]{"sasa", "sqd"});
    }
    
    /**
     * 方法的invoke只能调用静态方法
     *
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void reflectTest() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class c = Class.forName("com.xl.web.action.IndexAction");
        Method m = c.getMethod(Constant.LOADPAGE, PageLoadContext.class);
        PageLoadContext pc = new PageLoadContext();
        m.invoke(c, pc);
    }
    
    @Test
    public void demoTest() throws ClassNotFoundException {
        Class<? extends String> class1 = "Person".getClass();
        Class<?> class2 = Class.forName("java.lang.String");
        //都是java.lang.String类型
        System.out.println(class1 == class2);
    }
    
    /**
     * 获取静态成员
     */
    @Test
    public void staticFiledTest() throws NoSuchFieldException, IllegalAccessException {
        Field[] fields = LogTest.class.getFields();
        //只有获取public获取属性，没有就会报错
        Field log2 = LogTest.class.getField("log3");
        System.out.println(log2.get(null));
    }
    
    /**
     * 成员赋值测试
     */
    @Test
    public void filedTest() throws NoSuchFieldException, IllegalAccessException {
        Person p = new Person();
        Field field = p.getClass().getDeclaredField("birthday");
        //不加会抛异常，不能给私有变量赋值
        field.setAccessible(true);
        field.set(p, "这是生日");
        System.out.println(p);
    }
}
