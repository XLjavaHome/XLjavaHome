package com.xl.service;

import com.xl.entity.User;
import com.xl.mvc.dao.UserDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Brian on 2016/5/11.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public boolean hasMatchUser(String userName, String password) {
        int matchCount = userDao.getMatchCount(userName, password);
        return matchCount > 0;
    }

    public User findUserByUserName(String userName) {
        return userDao.findUserByUserName(userName);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }
}
