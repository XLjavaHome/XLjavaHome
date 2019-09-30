package com.example.demo.web;

import lombok.extern.log4j.Log4j;
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
@Log4j
public class IndexAction {
    /**
     * http://localhost:8080/index
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "我的第一个springboot程序";
    }
    
    /**
     * http://localhost:8080/index2?id=1231
     *
     * @param id
     * @return
     */
    @RequestMapping("/index2")
    public String index2(String id) {
        System.out.println(Thread.currentThread().getName() + ":" + id);
        return id;
    }
}
