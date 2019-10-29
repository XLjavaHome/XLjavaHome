package com.xl;

import com.xl.entity.Person;
import java.util.Date;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-10-29
 * @time 16:45
 * To change this template use File | Settings | File Templates.
 */
public class LombokTest {
    /**
     * 建造者模式
     */
    @Test
    void buildTest() {
        Person person = Person.builder().age(15).birthday(new Date()).name("张三").build();
        System.out.println(person);
    }
}
