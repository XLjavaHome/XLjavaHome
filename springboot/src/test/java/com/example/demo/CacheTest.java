package com.example.demo;

import com.example.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheTest {
    @Autowired
    private UserService userService;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private CacheManager cacheManager;
    
    @Test
    public void nulltest() {
        //空就抛出异常
        Assert.notNull(1, "Filter must not be null");
        Assert.notNull(null, "Filter must not be null");
    }
    
    @Test
    public void cacheTest() {
        System.out.println(userService.getUser(2));
        System.out.println(userService.getUser(2));
        System.out.println(userService.getUser3(2));
        System.out.println(userService.getUser3(2));
        System.out.println(userService.getUser4(2));
        System.out.println(userService.getUser4(2));
        System.out.println(userService.getUser5(2));
        System.out.println(userService.getUser5(2));
    }
    
    @Test
    public void test2() {
        //可以改变缓存
        System.out.println(cacheManager.getCacheNames());
        System.out.println(userService.getUser5(2));
        System.out.println(cacheManager.getCacheNames());
        System.out.println(userService.getUser5(2));
        System.out.println(cacheManager.getCacheNames());
    }
}
