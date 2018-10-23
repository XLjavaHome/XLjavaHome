package com.xl.spring;
/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-01-16
 * @Time: 10:37
 * To change this template use File | Settings | File Templates.
 */

import com.xl.entity.User;
import com.xl.service.PersonService;
import com.xl.service.SpringService;
import com.xl.service.UserService;
import com.xl.util.SpringMysqlJdbcUtil;
import java.io.IOException;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.@RunWith 注释标签是 Junit 提供的，用来说明此测试类的运行者，这里用了 SpringJUnit4ClassRunner，这个类是一个针对 Junit 运行环境的自定义扩展，用来标准化在 Spring 环境中 Junit4.5 的测试用例，例如支持的注释标签的标准化
 *
 * @ContextConfiguration 注释标签是 Spring test context 提供的，用来指定 Spring 配置信息的来源，支持指定 XML 文件位置或者 Spring 配置类名，这里我们指定 classpath 下的 /config/Spring-db1.xml 为配置文件的位置
 * @Transactional 注释标签是表明此测试类的事务启用，这样所有的测试方案都会自动的 rollback，即您不用自己清除自己所做的任何对数据库的变更了
 * @Autowired 体现了我们的测试类也是在 Spring 的容器中管理的，他可以获取容器的 bean 的注入，您不用自己手工获取要测试的 bean 实例了
 * testGetAccountById 是我们的测试用例：注意和上面的 AccountServiceOldTest 中相同的测试方法的对比，这里我们不用再 try-catch-finally 了，事务管理自动运行，当我们执行完成后，所有相关变更会被自动清除
 * <br/>
 * Spring引入多个配置文件用locations和大括号
 * @author: 徐立
 * @Date: 2018-05-03
 * @Time: 14:13
 * To chanmplate use File | Set14:13 | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = ConcertConfig.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml", "classpath:spring/applicationContext-dao-BasicDataSource.xml"})
@Log4j
public class SpringTest {
    ////可以直接初始化
    ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
    @Autowired
    private SpringMysqlJdbcUtil springMysqlJdbcUtil;
    @Autowired
    private SpringService carService;
    @Autowired
    private SpringService carService2;
    @Autowired
    private UserService userService;

    @Test
    public void demoTest() throws IOException {
        carService.addCar("测试11");
        //false不是一样的
        //System.out.println(carService == ctx.getBean(SpringService.class));
        //打开资源文件
        carService.addCar("测试22");
        carService.addCar("测试33");
        //Resource xlFile = ctx.getResource("file:d:\\xl.txt");
    }

    @Test
    public void resourceTest() {
        System.out.println(springMysqlJdbcUtil);
    }

    @Test
    public void equalTest() {
        //默认是单例的
        log.info(carService == carService2);
        System.out.println(carService == carService2);
    }

    @Test
    public void aopTest() {
        PersonService bean = ctx.getBean(PersonService.class);
        bean.save("aop切面开始！");
    }

    @Test
    public void databaseUser() {
        boolean b1 = userService.hasMatchUser("admin", "123456");
        boolean b2 = userService.hasMatchUser("admin", "1111");
        System.out.println(b1);
        System.out.println(b2);
        List<User> userList = userService.findAll();
        userList.forEach(user -> {
            System.out.println(user);
        });
    }
}
