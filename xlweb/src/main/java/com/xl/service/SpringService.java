package com.xl.service;

import com.xl.base.SpringDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-05-05
 * @Time: 21:04
 * To change this template use File | Settings | File Templates.
 */
@Service
//"prototype"
@Scope
public class SpringService {
    @Autowired
    private SpringDao carDao;

    public void addCar(String car) {
        this.carDao.insertCar(car);
    }
}