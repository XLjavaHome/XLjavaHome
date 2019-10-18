package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.log4j.Log4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-28
 * @time 18:15
 * To change this template use File | Settings | File Templates.
 */
//开启缓存注解，可以用于主类上。
@EnableCaching
@Service
@Log4j
public class UserService {
    /**
     * 数据库中的数据
     */
    public static final Map<Integer, User> users = new HashMap<>();
    public static final Map<String, User> nameMap = new HashMap<>();
    static {
        String text = "我是快乐鱼";
        User user = new User(text);
        users.put(1, user);
        users.put(2, new User("我是忧郁猫"));
        users.put(3, new User("我是昴先生"));
        nameMap.put(text, user);
    }
    /**
     * #p0的意思是指加有@Cacheable注解的方法中的第一个参数  #p2不会报错，但是结果会不对
     * #id也可以
     * 缓存注释SpEL表达式 不能使用字符串
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "user", key = "targetClass + methodName +#p0")
    public User getUser(int id) {
        log.info("缓存中没有，从map中获取" + id);
        User user = users.get(id);
        return user;
    }
    
    @Cacheable(cacheNames = "user", key = "#p0")
    public User getUser3(int id) {
        log.info("缓存中没有，从map中获取" + id);
        User user = users.get(id);
        return user;
    }
    
    @Cacheable(cacheNames = "user", key = "#p0")
    public User getUser4(int id) {
        log.info("缓存中没有，从map中获取" + id);
        User user = users.get(id);
        return user;
    }
    
    @Cacheable(cacheNames = "userxl", key = "targetClass + methodName +#p0")
    public User getUser5(int id) {
        log.info("缓存中没有，从map中获取" + id);
        User user = users.get(id);
        return user;
    }
    
    @Cacheable(cacheNames = "user", key = "targetClass + methodName +#p0")
    public User getUser2(String name) {
        log.info("缓存中没有，从namemap中获取" + name);
        User user = nameMap.get(name);
        return user;
    }
}
