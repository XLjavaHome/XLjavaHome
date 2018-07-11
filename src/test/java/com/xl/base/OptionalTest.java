package com.xl.base;

import com.xl.entity.User;
import com.xl.util.StringUtil;
import java.util.Optional;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-06-22
 * @Time: 10:36
 * To change this template use File | Settings | File Templates.
 */
public class OptionalTest {
    @Test
    public void emptyTest() throws Exception {
        User user = null;
        getUser(user);
        Optional.ofNullable(user).orElseGet(() -> createUser());
    }

    private User getUser(User user) throws Exception {
        return Optional.ofNullable(user).filter(u -> StringUtil.equals("张三", u.getName())).orElseGet(() -> {
            User u1 = new User();
            System.out.println("这是新建的User");
            return u1;
        });
    }

    private User createUser() {
        User u = new User();
        u.setName("张三");
        System.out.println(u);
        return u;
    }
}
