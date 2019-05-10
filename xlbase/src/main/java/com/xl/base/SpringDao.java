package com.xl.base;

import javax.annotation.PostConstruct;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 徐立
 * @Date: 2018-05-05
 * @Time: 21:03
 * To change this template use File | Settings | File Templates.
 */
@Log4j
@Repository
public class SpringDao {
    @PostConstruct
    public void init() {
        log.info("对象一出始化就执行的方法");
    }

    public void insertCar(String car) {
        log.info("这是：" + car);
    }
}
