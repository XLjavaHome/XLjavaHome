package com.xl;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created with IntelliJ IDEA.连接redis
 *
 * @author: 徐立
 * @Date: 2018-08-08
 * @Time: 9:18
 * To change this template use File | Settings | File Templates.
 */
public class JedisTest {
    public static final String ip = "localhost";
    public static final int port = 6379;
    public static final String password = "root";

    @Test
    public void demoTest() {
        Jedis jedis = new Jedis(ip, port);
        //密码
        jedis.auth(password);
        //jedis.set("foo", "bar");
        String value = jedis.get("foo");
        System.out.println("value=" + value);
        //关闭
        jedis.close();
    }

    /**
     * redis连接池
     */
    @Test
    public void poolTest() {
        //获取最大数据库连接池对象
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        //最大空闲连接数
        config.setMaxIdle(10);
        JedisPool jedisPool = new JedisPool(config, ip, port);
        Jedis jedis = jedisPool.getResource();
        jedis.auth(password);
        jedis.set("name", "张三");
        String value = jedis.get("name");
        System.out.println(value);
        jedisPool.close();
    }
}
