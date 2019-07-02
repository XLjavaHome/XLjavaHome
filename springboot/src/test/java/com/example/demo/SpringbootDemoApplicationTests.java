package com.example.demo;

import com.example.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDemoApplicationTests {
    @Autowired
    private UserService userService;
    
    @Test
    public void contextLoads() {
        System.out.println(userService.getUser(2));
        System.out.println(userService.getUser(3));
        System.out.println(userService.getUser2("我是快乐鱼"));
        System.out.println(userService.getUser2("我是快乐鱼"));
    }
}
