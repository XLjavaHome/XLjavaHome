package com.xl.json;

import com.xl.entity.User;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.junit.Test;

/**
 * @author 徐立
 * @Decription
 * @date 2014年4月13日
 */
@Log4j
public class JsonTest {
    @Test
    public void testnetSfJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("a", 1);
        jsonObject.put("a", 2);
        log.info(jsonObject);
    }

    /**
     * 这个需要下列5个jar包才可以
     * commons-beanutils-1.8.3.jar
     * commons-collections.jar
     * commons-lang.jar
     * commons-logging.jar
     * ezmorph-1.0.6.jar
     * json-lib-2.3-jdk15.jar
     */
    @Test
    public void test1() {
        User user = new User();
        user.setName("phl");
        user.setAge(25);
        User user2 = new User();
        user2.setName("luckybird");
        user2.setAge(26);
        List<User> list = new ArrayList<User>();
        list.add(user);
        list.add(user2);
        JSONObject json = JSONObject.fromObject(user);
        log.info(json);
        log.info(json.toString());
        JSON json2 = JSONSerializer.toJSON(list);
        log.info(json2.toString());
        json2 = JSONSerializer.toJSON(new User());
        log.info(json2.toString());
    }

    @Test
    public void nullTest() {
        //没有报错返回空
        User user = new User();
        user.setName(null);
        log.info(JSONObject.fromObject(user));
    }
}
