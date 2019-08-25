import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-08-25
 * @time 23:44
 * To change this template use File | Settings | File Templates.
 */
public class RedisTest {
    @org.junit.jupiter.api.Test
    void name() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(5);
        config.setTestOnBorrow(false);
        JedisPool jedisPool = new JedisPool(config, "127.0.0.1", 6379);
        Jedis jedis = jedisPool.getResource();
        jedis.auth("root");
        jedis.set("name", "张三");
        String name = jedis.get("name");
        System.out.println(name);
    }
}
