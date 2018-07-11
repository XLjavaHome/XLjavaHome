package com.xl.web.controller;

import com.xl.entity.User;
import com.xl.service.SpringService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-05-20
 * @Time: 21:11
 * To change this template use File | Settings | File Templates.
 */
@Log4j
@Controller
@RequestMapping(value = "/springController")
public class HelloController {
    @Resource
    private SpringService service;

    @RequestMapping(value = "/spring", method = RequestMethod.GET)
    public String springContronller(Model model) {
        service.addCar("这是springMVC");
        log.info("这是helloWorld");
        model.addAttribute("msg", "你好spring mvc");
        return "/index.jsp";
    }

    @RequestMapping(value = "{name}", method = RequestMethod.GET)
    @ResponseBody
    public User getShopInJSON(@PathVariable String name) {
        User user = new User();
        user.setName(name);
        log.info(name);
        return user;
    }
}
