package com.xl.service.impl;

import com.xl.service.PersonService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-05-08
 * @Time: 13:01
 * To change this template use File | Settings | File Templates.
 */
@Service
public class PersonServiceBean implements PersonService {
    @Override
    public void save(String name) {
        System.out.println("我是save方法");
    }

    @Override
    public void update(String name, Integer id) {
        System.out.println("我是update()方法");
    }

    @Override
    public String getPersonName(Integer id) {
        System.out.println("我是getPersonName()方法");
        return "xxx";
    }
}
