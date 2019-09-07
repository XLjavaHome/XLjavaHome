package com.xl;

import com.xl.entity.Person;
import com.xl.util.FileUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import lombok.extern.log4j.Log4j;
import org.apache.poi.util.IOUtils;
import org.junit.Test;
;
//package com.xl;

/**
 * Created with 徐立.一个用户自定义的类都是利用扩展加载器的方式进行加载的.但是java.util.Date或者是java.lang.String上是有加载器的,但是java里面为了保证程序的安全,提供了一个概念--双生加载.如果是系统类库,则使用自己的加载器.外部无法控制,而如果用户有需要也可以定义特定的加载,而这两个加载器没有冲突.
 * 目的:为了程序的安全,就算定义了重名的类名称,类也不会加载
 * <p>
 *         
 *
 * @author 徐立
 * @date 2019-05-10
 * @time 22:47
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class ClassLoaderTest {
    @Test
    public void name() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClasssLoader myClasss = new MyClasssLoader();
        URL resource = Thread.currentThread().getContextClassLoader().getResource("Person.class");
        File file = new File(resource.getFile());
        myClasss.loadClass(file.getAbsolutePath());
        //全类名
        String className = "com.xl.entity.Person";
        Class<?> clazz = Class.forName(className, true, myClasss);
        Object obj = clazz.newInstance();
        System.out.println(obj);
        //后面的类加载器都是appClassLoad
        System.out.println(obj.getClass().getClassLoader());//打印
        Person person = new Person();
        System.out.println(person.getClass().getClassLoader());
        Class<?> name = Class.forName(className);
        Object obj2 = name.newInstance();
        System.out.println(obj);
        System.out.println(obj2.getClass().getClassLoader());
    }
    
    private void getContent(File file) throws IOException {
        StringBuilder content = FileUtil.getContent(file);
        System.out.println(content);
    }
    
    private void demo1() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        ClassLoader classLoader1 = Thread.currentThread().getContextClassLoader();
        //一样
        log.info(classLoader);
        log.info(classLoader1);
        System.out.println(classLoader == classLoader1);
    }
    
    @Test
    public void parentTest() {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        log.info(contextClassLoader);
        log.info(contextClassLoader.getParent());
        log.info(contextClassLoader.getParent().getParent());
    }
    
    /**
     * 判断项目中是否有slf4j的实现类
     */
    @Test
    public void 判断类是否存在() throws IOException {
        //换成点 找不到
        String name = "org/slf4j/impl/StaticLogger.class";
        URL resource = Thread.currentThread().getContextClassLoader().getResource(name);
        System.out.println(resource);
        Enumeration<URL> resources = ClassLoader.getSystemResources(name);
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            System.out.println(url.toString());
        }
    }
    
    static class MyClasssLoader extends ClassLoader {
        /**
         * 必须加载字节码
         *
         * @param name
         * @return
         * @throws ClassNotFoundException
         */
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            byte[] bytes = null;
            File file = new File(name);
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                bytes = IOUtils.toByteArray(fileInputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return defineClass(null, bytes, 0, bytes.length);
        }
    }
}
