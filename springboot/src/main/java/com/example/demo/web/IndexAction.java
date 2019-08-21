package com.example.demo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-08-21
 * @time 23:23
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class IndexAction {
    @RequestMapping("/index")
    public String index() {
        return "我的第一个springboot程序";
    }
}
