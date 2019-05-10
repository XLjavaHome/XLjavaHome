package com.xl.service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 徐立
 * @Date: 2018-05-08
 * @Time: 11:48
 * To change this template use File | Settings | File Templates.
 */
public interface PersonService {
    void save(String name);

    void update(String name, Integer id);

    String getPersonName(Integer id);
}
