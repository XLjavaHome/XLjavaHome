package com.xl;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-09-19
 * @time 21:57
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        Integer integer = jdbcTemplate.queryForObject("select 3 from dual ", Integer.class);
        System.out.println(integer);
    }
}
