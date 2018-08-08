package com.xl;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-08-08
 * @Time: 9:18
 * To change this template use File | Settings | File Templates.
 */
public class JedisTest {
    @Test
    public void demoTest() {
        Jedis jedis = new Jedis("localhost", 6379);
        //密码
        jedis.auth("root");
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        System.out.println("value=" + value);
    }
}
